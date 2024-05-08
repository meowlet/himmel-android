package com.meow.himmel.domain.use_case.user

import com.meow.himmel.domain.model.Response
import com.meow.himmel.domain.model.User
import com.meow.himmel.domain.repository.UserRepository
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class UpdateUser(
    private val repository: UserRepository
) {
    private fun fieldValidate(user: User): Boolean {
        return user.email.isNotEmpty() && user.userName.isNotEmpty() && user.displayName.isNotEmpty()
    }

    operator fun invoke(
        user: User
    ) = flow {
        when {
            !fieldValidate(user) -> {
                emit(Response.Failure(Exception("Please fill all fields")))
            }

            else -> {
                emitAll(repository.updateUser(user))
            }
        }
    }
}