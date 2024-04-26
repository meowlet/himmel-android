package com.meow.himmel.domain.use_case.auth

import com.meow.himmel.domain.model.Response
import com.meow.himmel.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class SignUp(
    private val repository: AuthRepository
) {
    private fun fieldValidate(email: String, password: String): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }

    private fun passwordValidate(password: String, repeatPassword: String): Boolean {
        return password == repeatPassword
    }

    operator fun invoke(
        email: String,
        password: String,
        repeatPassword: String
    ): Flow<Response<Boolean>> = flow {
        when {
            !fieldValidate(email, password) -> {
                emit(Response.Failure(Exception("Please fill all fields")))
            }

            !passwordValidate(password, repeatPassword) -> {
                emit(Response.Failure(Exception("Password doesn't match")))
            }

            else -> {
                emitAll(repository.signUp(email, password))
            }
        }
    }
}