package com.meow.himmel.presentation.sign_up

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.meow.himmel.domain.model.Response
import com.meow.himmel.presentation.common.CustomAlertDialog
import com.meow.himmel.presentation.common.ErrorText
import com.meow.himmel.presentation.common.HimmelLogo
import com.meow.himmel.presentation.common.IconButton
import com.meow.himmel.presentation.common.SingleLinedPasswordField
import com.meow.himmel.presentation.common.SingleLinedTextField
import com.meow.himmel.presentation.sign_in.SignInEvent
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.brands.Facebook
import compose.icons.fontawesomeicons.brands.Google
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SignUpScreen(
    state: SignUpState,
    navController: NavController = rememberNavController(),
    uiEvent: SharedFlow<SignUpUiEvent> = MutableSharedFlow(),
    onEvent: (SignUpEvent) -> Unit
) {


    LaunchedEffect(key1 = true) {
        uiEvent.collectLatest { uiEvent ->
            when (uiEvent) {
                is SignUpUiEvent.Navigate -> navController.navigate(uiEvent.route)
            }
        }
    }


    Scaffold { padding ->


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(vertical = 24.dp)
        ) {


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
            ) {

                HimmelLogo(
                    modifier = Modifier.padding(48.dp)
                )

                state.signUpResponse.let { response ->
                    when (response) {
                        is Response.Success -> {
                            CustomAlertDialog(
                                onDismissRequest = {
                                    onEvent(SignUpEvent.Idle)
                                },
                                onConfirmation = {
                                    onEvent(SignUpEvent.Done)
                                },
                                dialogTitle = "Sign up successful!",
                                dialogText = "You can now sign in with your email and password. Don't forget to verify your email!",
                                icon = Icons.Default.Done,
                                confirmText = "Sign in now",
                                dismissText = "Stay here"
                            )
                        }

                        is Response.Failure -> {
                            ErrorText(
                                text = response.exception.message ?: "An error occurred",
                                isCentered = true
                            )
                        }

                        is Response.Loading -> {
                            CircularProgressIndicator()
                        }

                        is Response.Idling -> {
                            // Do nothing
                        }
                    }
                }

                SingleLinedTextField(text = state.email, label = "Your email address") {
                    onEvent(SignUpEvent.EmailChange(it))
                }
                SingleLinedPasswordField(
                    text = state.password,
                    label = "Password",
                ) {
                    onEvent(SignUpEvent.PasswordChange(it))
                }
                SingleLinedPasswordField(
                    text = state.confirmPassword,
                    label = "Confirm Password",
                ) {
                    onEvent(SignUpEvent.ConfirmPasswordChange(it))
                }

                Button(modifier = Modifier.fillMaxWidth(0.75f), onClick = {
                    onEvent(SignUpEvent.SignUp)
                }) {
                    Text(text = "Sign Up", style = MaterialTheme.typography.titleMedium)
                }

                Text(
                    text = "Already have an account? Sign in now",
                    style = MaterialTheme.typography.bodyMedium,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        onEvent(SignUpEvent.SignIn)
                    }
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
            ) {
                IconButton(
                    isOutlined = true,
                    icon = FontAwesomeIcons.Brands.Google,
                    text = "Sign up with Google"
                ) {
                    // Do nothing
                }
                IconButton(
                    isOutlined = true,
                    icon = FontAwesomeIcons.Brands.Facebook,
                    text = "Sign up with Facebook"
                ) {
                    // Do nothing
                }
            }
        }
    }
}

@Preview
@Composable
private fun SignUpPreview() {

    SignUpScreen(state = SignUpState(), onEvent = {})
}






