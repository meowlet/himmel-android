package com.meow.himmel.domain.repository

import com.meow.himmel.domain.model.Response
import com.meow.himmel.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserInfo(): User
    fun updateUser(user: User): Flow<Response<Unit>>
    fun deleteUser(): Flow<Response<Unit>>
}