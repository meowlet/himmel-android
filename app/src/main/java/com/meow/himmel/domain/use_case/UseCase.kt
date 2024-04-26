package com.meow.himmel.domain.use_case

import com.meow.himmel.domain.use_case.auth.SignUp

data class UseCase(
    val signUp: SignUp,
)