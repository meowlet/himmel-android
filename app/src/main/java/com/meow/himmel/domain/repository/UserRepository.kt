package com.meow.himmel.domain.repository

import android.net.Uri
import com.meow.himmel.domain.model.Response
import com.meow.himmel.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserInfo(userId: String): Flow<Response<User>>
    fun updateUser(user: User): Flow<Response<Unit>>
    fun deleteUser(): Flow<Response<Unit>>
    fun updateAvatar(uri: Uri): Flow<Response<Unit>>
}