package com.meow.himmel.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.meow.himmel.domain.model.Response
import com.meow.himmel.domain.repository.AuthRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override fun isUserSignedIn(): Boolean = firebaseAuth.currentUser != null

    override fun signIn(email: String, password: String) = flow {
        emit(Response.Loading)
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(Response.Success(Unit))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }

    override fun signUp(email: String, password: String) = flow {
        emit(Response.Loading)
        try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            emit(Response.Success(Unit))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }

    override fun signOut() = flow {
        emit(Response.Loading)
        try {
            firebaseAuth.signOut()
            emit(Response.Success(Unit))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }
}