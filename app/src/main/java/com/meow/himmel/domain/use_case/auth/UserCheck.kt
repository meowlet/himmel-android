package com.meow.himmel.domain.use_case.auth

import com.meow.himmel.domain.repository.AuthRepository

class UserCheck(
    private val repository: AuthRepository
) {
    operator fun invoke(): Boolean {
        return repository.isUserSignedIn()
    }
}