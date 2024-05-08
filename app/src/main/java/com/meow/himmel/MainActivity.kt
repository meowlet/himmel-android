package com.meow.himmel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.meow.himmel.presentation.home.HomeScreen
import com.meow.himmel.presentation.main_layout.MainLayoutViewModel
import com.meow.himmel.presentation.main_layout.MainLayout
import com.meow.himmel.presentation.more.MoreScreen
import com.meow.himmel.presentation.profile.ProfileScreen
import com.meow.himmel.presentation.sign_in.SignInScreen
import com.meow.himmel.presentation.sign_in.SignInViewModel
import com.meow.himmel.presentation.sign_up.SignUpScreen
import com.meow.himmel.presentation.sign_up.SignUpViewModel
import com.meow.himmel.presentation.util.AuthScreen
import com.meow.himmel.presentation.util.Screen
import com.meow.himmel.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val signUpViewModel: SignUpViewModel by viewModels()
    private val singInViewModel: SignInViewModel by viewModels()
    private val mainLayoutViewModel: MainLayoutViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val navController = rememberNavController()
                MainLayout(
                    navController = navController,
                    state = mainLayoutViewModel.state.value,
                    onEvent = mainLayoutViewModel::onEvent,
                    uiEvent = mainLayoutViewModel.eventFlow
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.AuthScreen.route
                    ) {
                        navigation(
                            route = Screen.AuthScreen.route,
                            startDestination = AuthScreen.SignIn.route
                        ) {
                            composable(AuthScreen.SignUp.route) {
                                SignUpScreen(
                                    navController = navController,
                                    state = signUpViewModel.state.value,
                                    onEvent = signUpViewModel::onEvent,
                                    uiEvent = signUpViewModel.eventFlow
                                )
                            }
                            composable(AuthScreen.SignIn.route) {
                                SignInScreen(
                                    navController = navController,
                                    state = singInViewModel.state.value,
                                    onEvent = singInViewModel::onEvent,
                                    uiEvent = singInViewModel.eventFlow
                                )
                            }
                        }

                        navigation(
                            route = Screen.MainScreen.route,
                            startDestination = com.meow.himmel.presentation.util.MainScreen.Home.route
                        ) {
                            composable(com.meow.himmel.presentation.util.MainScreen.Home.route) {
                                HomeScreen()
                            }
                            composable(com.meow.himmel.presentation.util.MainScreen.Profile.route) {
                                ProfileScreen()
                            }
                            composable(com.meow.himmel.presentation.util.MainScreen.More.route) {
                                MoreScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}





