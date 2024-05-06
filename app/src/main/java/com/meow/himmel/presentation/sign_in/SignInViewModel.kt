package com.meow.himmel.presentation.sign_in

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meow.himmel.domain.model.Response
import com.meow.himmel.domain.use_case.UseCase
import com.meow.himmel.presentation.util.AuthScreen
import com.meow.himmel.presentation.util.CoreScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private val _state = mutableStateOf(SignInState())
    val state: State<SignInState> = _state

    private val _eventFlow = MutableSharedFlow<SignInUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.UserCheck -> {
                if (useCase.userCheck()) {
                    _state.value = state.value.copy(hasUser = true)
                }
            }

            is SignInEvent.EmailChange -> _state.value =
                state.value.copy(email = event.email, signInResponse = Response.Idling)

            is SignInEvent.PasswordChange -> _state.value =
                state.value.copy(password = event.password, signInResponse = Response.Idling)

            is SignInEvent.SignIn -> signIn(
                state.value.email,
                state.value.password
            )

            is SignInEvent.Idle -> _state.value =
                state.value.copy(signInResponse = Response.Idling)

            is SignInEvent.Success -> viewModelScope.launch {
                _state.value = state.value.copy(
                    signInResponse = Response.Idling
                )
                _eventFlow.emit(SignInUiEvent.ShowToast("Sign in success, taking you to the main screen"))
                _eventFlow.emit(SignInUiEvent.Navigate(CoreScreen.MainScreen.route))
            }

            SignInEvent.SignUp -> viewModelScope.launch {
                _eventFlow.emit(SignInUiEvent.Navigate(AuthScreen.SignUp.route))
            }
        }
    }

    private fun signIn(email: String, password: String) {
        viewModelScope.launch {
            useCase.signIn(email, password).collect {
                _state.value = state.value.copy(signInResponse = it)
            }
        }
    }
}


