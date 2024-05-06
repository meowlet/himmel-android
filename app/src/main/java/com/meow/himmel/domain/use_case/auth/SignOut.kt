package com.meow.himmel.domain.use_case.auth

import com.meow.himmel.domain.repository.AuthRepository
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class SignOut(
    private val repository: AuthRepository
) {
    operator fun invoke() = flow {
        emitAll(repository.signOut())
    }
}