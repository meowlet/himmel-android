package com.meow.himmel.domain.use_case.user

import com.meow.himmel.domain.repository.UserRepository

class GetUserInfo(
    private val repository: UserRepository
) {
    operator fun invoke(userId: String) = repository.getUserInfo(userId)
}