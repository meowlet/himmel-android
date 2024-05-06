package com.meow.himmel.presentation.main_layout

sealed class MainLayoutEvent {
    data class NavigationChange(val route: String) : MainLayoutEvent()
}

sealed class MainLayoutUiEvent {
    data class Navigate(val route: String) : MainLayoutUiEvent()
}