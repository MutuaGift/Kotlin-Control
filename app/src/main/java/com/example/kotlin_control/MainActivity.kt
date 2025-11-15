package com.example.kotlin_control // Your project's package name

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

import androidx.navigation.compose.rememberNavController
import com.example.kotlin_control.ui.theme.KotlinControlTheme
// Make sure to import your new LoginScreen
// (If Android Studio doesn't do it, add this line)
import com.example.kotlin_control.loginPage.LoginScreen
import com.example.kotlin_control.navigation.NavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            KotlinControlTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // --- This is the only line you need to change ---
                    NavGraph(
                        navController = navController
                    )
                }
            }
        }
    }
}