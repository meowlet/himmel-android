package com.meow.himmel.presentation.main_layout

import com.meow.himmel.presentation.util.MainScreen

data class MainLayoutState (
    val currentRoute: String = MainScreen.Home.route,
)