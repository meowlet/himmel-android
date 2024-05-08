package com.meow.himmel.domain.use_case.user

import com.meow.himmel.domain.repository.UserRepository

class DeleteUser(
    private val repository: UserRepository
) {
    operator fun invoke() = repository.deleteUser()
}