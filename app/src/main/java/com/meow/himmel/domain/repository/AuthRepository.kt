package com.meow.himmel.domain.repository
import com.meow.himmel.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun isUserLoggedIn(): Boolean
    fun signIn(email: String, password: String): Flow<Response<Boolean>>
    fun signUp(email: String, password: String): Flow<Response<Boolean>>
    fun signOut(): Flow<Response<Boolean>>
}