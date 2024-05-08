package com.meow.himmel.domain.model

data class User (
    val id: String = "user.id",
    val email: String = "user.email",
    val userName: String = "user.name",
    val displayName: String = "user.displayName",
    val bio: String = "user.bio",
    val photoUrl: String = "user.photoUrl",
)