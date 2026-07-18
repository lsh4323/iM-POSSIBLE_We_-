package com.example.myapplication.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object House : Screen("house")
    data object Menu : Screen("menu")
    data object DeveloperMode : Screen("developer_mode")
}