package com.meow.himmel.domain.repository
import com.meow.himmel.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun isUserSignedIn(): Boolean
    fun signIn(email: String, password: String): Flow<Response<Unit>>
    fun signUp(email: String, password: String): Flow<Response<Unit>>
    fun signOut(): Flow<Response<Unit>>
}