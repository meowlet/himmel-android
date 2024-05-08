package com.meow.himmel.data.repository

import android.net.Uri
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.storage
import com.meow.himmel.domain.model.Response
import com.meow.himmel.domain.model.User
import com.meow.himmel.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(
    private val database: DatabaseReference
) : UserRepository {
    override fun getUserInfo(userId: String): Flow<Response<User>> = flow {
        emit(Response.Loading)
        try {
            val dataSnapshot = database.child(userId).get().await()
            val user = dataSnapshot.getValue(User::class.java)
            if (user != null) {
                emit(Response.Success(user))
            } else {
                throw Exception("User not found")
            }
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }

    override fun updateUser(user: User): Flow<Response<Unit>> = flow {
        emit(Response.Loading)
        try {
            database.child(user.id).setValue(user).await()
            emit(Response.Success(Unit))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }

    override fun deleteUser(): Flow<Response<Unit>> = flow {
        emit(Response.Loading)
        try {
            val userId = FirebaseAuth.getInstance().currentUser?.uid
            if (userId != null) {
                database.child(userId).removeValue().await()
                emit(Response.Success(Unit))
            } else {
                throw Exception("User not found")
            }
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }

    override fun updateAvatar(uri: Uri): Flow<Response<Unit>> = flow {
        emit(Response.Loading)
        try {
            val userId = FirebaseAuth.getInstance().currentUser?.uid
            if (userId != null) {
                val storageRef = Firebase.storage.reference.child("avatars/$userId")
                storageRef.putFile(uri).await()
                val downloadUrl = storageRef.downloadUrl.await()
                database.child(userId).child("avatarUrl").setValue(downloadUrl.toString()).await()
                emit(Response.Success(Unit))
            } else {
                emit(Response.Failure(Exception("User not found")))
            }
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }
}