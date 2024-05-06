package com.meow.himmel.presentation.sign_up

import com.meow.himmel.domain.model.Response

data class SignUpState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val signUpResponse: Response<Unit> = Response.Idling,
)