package com.example.kotlin_control.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kotlin_control.ProfileScreen
import com.example.kotlin_control.loginPage.LoginScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Routes.Login.route
        // Add your NavController and other parameters here
    ) {

        composable(Routes.Login.route) {
            LoginScreen(
                navController = navController,
                onLoginSuccess = {
                    navController.navigate(Routes.Profile.route) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                }

            )
        }

        composable(Routes.Home.route) {
            // Call your HomeScreen composable here
        }

        composable(Routes.Profile.route) {
            ProfileScreen()
        }
        // Define your composable destinations here
    }

}