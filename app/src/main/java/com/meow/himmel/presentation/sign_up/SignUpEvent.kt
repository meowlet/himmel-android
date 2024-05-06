package com.meow.himmel.presentation.sign_up

sealed class SignUpEvent {
    data class EmailChange(val email: String) : SignUpEvent()
    data class PasswordChange(val password: String) : SignUpEvent()
    data class ConfirmPasswordChange(val confirmPassword: String) : SignUpEvent()
    data object SignUp : SignUpEvent()
    data object Idle : SignUpEvent()
    data object Done : SignUpEvent()
    data object SignIn : SignUpEvent()
}

sealed class SignUpUiEvent {
    data class Navigate(val route: String) : SignUpUiEvent()
}


