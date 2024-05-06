package com.meow.himmel.domain.model

data class User (
    val userId: String,
    val email: String,
    val userName: String,
    val displayName: String,
    val bio: String,
    val photoUrl: String,
)