package com.meow.himmel.presentation.sign_up

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meow.himmel.domain.model.Response
import com.meow.himmel.domain.use_case.UseCase
import com.meow.himmel.presentation.util.AuthScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private val _state = mutableStateOf(SignUpState())
    val state: State<SignUpState> = _state

    private val _eventFlow = MutableSharedFlow<SignUpUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.EmailChange -> _state.value =
                state.value.copy(email = event.email, signUpResponse = Response.Idling)

            is SignUpEvent.PasswordChange -> _state.value =
                state.value.copy(password = event.password, signUpResponse = Response.Idling)

            is SignUpEvent.ConfirmPasswordChange -> _state.value =
                state.value.copy(
                    confirmPassword = event.confirmPassword,
                    signUpResponse = Response.Idling
                )

            is SignUpEvent.SignUp -> signUp(
                state.value.email,
                state.value.password,
                state.value.confirmPassword
            )

            is SignUpEvent.Idle -> _state.value = state.value.copy(signUpResponse = Response.Idling)

            is SignUpEvent.Done -> viewModelScope.launch {
                _state.value = state.value.copy(signUpResponse = Response.Idling)
                _eventFlow.emit(SignUpUiEvent.Navigate(AuthScreen.SignIn.route))
            }

            SignUpEvent.SignIn -> viewModelScope.launch {
                _eventFlow.emit(SignUpUiEvent.Navigate(AuthScreen.SignIn.route))
            }
        }
    }

    private fun signUp(email: String, password: String, confirmPassword: String) {
        viewModelScope.launch {
            useCase.signUp(email, password, confirmPassword).collect {
                _state.value = state.value.copy(signUpResponse = it)
            }
        }
    }
}

