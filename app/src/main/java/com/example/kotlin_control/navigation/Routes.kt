package com.example.kotlin_control.navigation

sealed class Routes (
    val route: String,
    val hasBottomBar: Boolean = false,
    val hasTopBar: Boolean = false
){

    object Login : Routes("login", hasBottomBar = false, hasTopBar = false)
    object Home : Routes("home", hasBottomBar = true, hasTopBar = true)
    object Profile : Routes("profile", hasBottomBar = true, hasTopBar = true)
}