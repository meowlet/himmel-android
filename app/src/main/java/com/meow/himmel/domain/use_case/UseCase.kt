package com.meow.himmel.domain.use_case

import com.meow.himmel.domain.use_case.auth.SignIn
import com.meow.himmel.domain.use_case.auth.UserCheck
import com.meow.himmel.domain.use_case.auth.SignOut
import com.meow.himmel.domain.use_case.auth.SignUp

data class UseCase(
    val userCheck: UserCheck,
    val signUp: SignUp,
    val signIn: SignIn,
    val signOut: SignOut
)