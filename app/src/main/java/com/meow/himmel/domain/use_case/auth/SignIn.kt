package com.meow.himmel.domain.use_case.auth

import com.meow.himmel.domain.model.Response
import com.meow.himmel.domain.repository.AuthRepository
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class SignIn(
    private val repository: AuthRepository
) {
    private fun fieldValidate(email: String, password: String): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }

    operator fun invoke(
        email: String, password: String
    ) = flow {
        when {
            !fieldValidate(email, password) -> {
                emit(Response.Failure(Exception("Please fill all fields")))
            }

            else -> {
                emitAll(repository.signIn(email, password))
            }
        }
    }
}