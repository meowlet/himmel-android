package com.meow.himmel.presentation.sign_in

import com.meow.himmel.domain.model.Response

data class SignInState(
    val email: String = "",
    val password: String = "",
    val hasUser: Boolean = false,
    val signInResponse: Response<Unit> = Response.Idling,
)