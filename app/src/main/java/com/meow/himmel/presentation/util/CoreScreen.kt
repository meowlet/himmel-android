package com.meow.himmel.presentation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.Smile

sealed class CoreScreen(
    val route: String,
) {
    data object AuthScreen : CoreScreen("auth_screen")
    data object MainScreen : CoreScreen("main_screen")
}

sealed class AuthScreen(val route: String) {
    data object SignIn : AuthScreen("sign_in")
    data object SignUp : AuthScreen("sign_up")
}

sealed class MainScreen(
    val route: String,
    val name: String = route,
    val selectedIcon: ImageVector = FontAwesomeIcons.Regular.Smile,
    val unselectedIcon: ImageVector = FontAwesomeIcons.Regular.Smile,
) {
    data object Home : MainScreen(
        route = "home",
        name = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
    )

    data object Profile : MainScreen(
        route = "profile",
        name = "Profile",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person
    )

    data object More : MainScreen(
        route = "more",
        name = "More",
        selectedIcon = Icons.Filled.Menu,
        unselectedIcon = Icons.Outlined.Menu
    )
}
