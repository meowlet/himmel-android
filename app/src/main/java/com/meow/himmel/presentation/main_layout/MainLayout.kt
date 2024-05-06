package com.meow.himmel.presentation.main_layout

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.meow.himmel.presentation.util.MainScreen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MainLayout(
    navController: NavController,
    state: MainLayoutState = MainLayoutState(),
    uiEvent: SharedFlow<MainLayoutUiEvent> = MutableSharedFlow(),
    onEvent: (MainLayoutEvent) -> Unit,
    content: @Composable () -> Unit,
) {

    val navItems = listOf(
        MainScreen.Home,
        MainScreen.Profile,
        MainScreen.More
    )

    LaunchedEffect(key1 = Unit) {
        uiEvent.collectLatest { uiEvent ->
            when (uiEvent) {
                is MainLayoutUiEvent.Navigate -> navController.navigate(uiEvent.route)
            }
        }
    }

    println(state.currentRoute)

    Scaffold(
        bottomBar = {
            NavigationBar {
                navItems.forEach { item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = if (state.currentRoute == item.route) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.name
                            )
                        },
                        label = {
                            Text(text = item.name)
                        },
                        selected = state.currentRoute == item.route,
                        onClick = {
                            onEvent(MainLayoutEvent.NavigationChange(item.route))
                        }
                    )
                }
            }
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            content()
        }
    }
}