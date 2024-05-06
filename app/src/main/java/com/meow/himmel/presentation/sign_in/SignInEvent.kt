package com.meow.himmel.presentation.sign_in

sealed class SignInEvent {

    data object UserCheck : SignInEvent()
    data class EmailChange(val email: String) : SignInEvent()
    data class PasswordChange(val password: String) : SignInEvent()
    data object SignIn : SignInEvent()
    data object SignUp : SignInEvent()
    data object Idle : SignInEvent()
    data object Success : SignInEvent()
}

sealed class SignInUiEvent {
    data class Navigate(val route: String) : SignInUiEvent()
    data class ShowToast(val message: String) : SignInUiEvent()
}


