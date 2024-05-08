package com.meow.himmel.domain.use_case

import com.meow.himmel.domain.use_case.auth.SignIn
import com.meow.himmel.domain.use_case.auth.SignOut
import com.meow.himmel.domain.use_case.auth.SignUp
import com.meow.himmel.domain.use_case.auth.UserCheck
import com.meow.himmel.domain.use_case.user.DeleteUser
import com.meow.himmel.domain.use_case.user.GetUserInfo
import com.meow.himmel.domain.use_case.user.UpdateAvatar
import com.meow.himmel.domain.use_case.user.UpdateUser

data class UseCase(
    val userCheck: UserCheck,
    val signUp: SignUp,
    val signIn: SignIn,
    val signOut: SignOut,
    val getUserInfo: GetUserInfo,
    val deleteUser: DeleteUser,
    val updateUser: UpdateUser,
    val updateAvatar: UpdateAvatar
)