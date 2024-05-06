package com.meow.himmel.presentation.sign_in

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.meow.himmel.domain.model.Response
import com.meow.himmel.presentation.common.ErrorText
import com.meow.himmel.presentation.common.HimmelLogo
import com.meow.himmel.presentation.common.IconButton
import com.meow.himmel.presentation.common.SingleLinedTextField
import com.meow.himmel.presentation.common.SingleLinedToggleablePasswordField
import com.meow.himmel.presentation.util.CoreScreen
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.brands.Facebook
import compose.icons.fontawesomeicons.brands.Google
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SignInScreen(
    state: SignInState,
    navController: NavController = rememberNavController(),
    uiEvent: SharedFlow<SignInUiEvent> = MutableSharedFlow(),
    onEvent: (SignInEvent) -> Unit
) {

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        onEvent(SignInEvent.UserCheck)
        uiEvent.collectLatest { uiEvent ->
            when (uiEvent) {
                is SignInUiEvent.Navigate -> navController.navigate(uiEvent.route)
                is SignInUiEvent.ShowToast -> {
                    Toast.makeText(context, uiEvent.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    LaunchedEffect(key1 = state.hasUser) {
        if (state.hasUser) {
            onEvent(SignInEvent.Success)
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

                when (state.signInResponse) {
                    is Response.Success -> {
                        onEvent(SignInEvent.Success)
                    }

                    is Response.Failure -> {
                        ErrorText(
                            text = state.signInResponse.exception.message ?: "An error occurred",
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

                SingleLinedTextField(text = state.email, label = "Your email address") {
                    onEvent(SignInEvent.EmailChange(it))
                }
                SingleLinedToggleablePasswordField(
                    text = state.password,
                    label = "Password",
                ) {
                    onEvent(SignInEvent.PasswordChange(it))
                }

                Button(modifier = Modifier.fillMaxWidth(0.75f), onClick = {
                    onEvent(SignInEvent.SignIn)
                }) {
                    Text(text = "Sign In", style = MaterialTheme.typography.titleMedium)
                }

                Text(
                    text = "Don't have an account? Sign up",
                    style = MaterialTheme.typography.bodyMedium,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        onEvent(SignInEvent.SignUp)
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
                    text = "Sign in with Google"
                ) {
                    // Do nothing
                }
                IconButton(
                    isOutlined = true,
                    icon = FontAwesomeIcons.Brands.Facebook,
                    text = "Sign in with Facebook"
                ) {
                    // Do nothing
                }
            }
        }
    }
}

@Preview
@Composable
private fun aslfdj() {
    SignInScreen(
        state = SignInState(),
        onEvent = {}
    )
}







