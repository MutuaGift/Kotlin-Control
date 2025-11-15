package com.example.kotlin_control.loginPage // Your package name

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    googleAuthViewModel: GoogleAuthViewModel = viewModel(),
    onLoginSuccess: () -> Unit,
    navController: NavController
) {
    // State variables for the text fields
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // State variable for the password visibility
    var passwordVisible by remember { mutableStateOf(false) }

    // collect google authstate
    val googleAuthState by googleAuthViewModel.authState.collectAsState()


    // Snackbar state
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()


    LaunchedEffect(googleAuthState) {
        when (googleAuthState) {
            is AuthState.Success -> {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Login successful!",
                        duration = SnackbarDuration.Short
                    )
                }

                //delay(2000)

                // Navigate to home screen
                onLoginSuccess()

                googleAuthViewModel.resetAuthState()

            }
            is AuthState.Error -> {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = (googleAuthState as AuthState.Error).message,
                        duration = SnackbarDuration.Long
                    )
                }

            }
            else -> {}

        }

    }
    // Main layout column
    Column(
        modifier = Modifier
            .fillMaxSize() // Fill the whole screen
            .padding(16.dp), // Add padding
        verticalArrangement = Arrangement.Center, // Center vertically
        horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
    ) {

        // --- 1. App Logo ---
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "App Logo",
            modifier = Modifier.size(100.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // --- 2. Email Field ---
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- 3. Password Field (with visibility toggle) ---
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            // Show/hide text based on 'passwordVisible' state
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            // The icon at the end of the field
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else
                    Icons.Filled.VisibilityOff

                val description = if (passwordVisible) "Hide password" else "Show password"

                // Clickable icon
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // --- 4. Log In Button ---
        Button(
            onClick = {
                // Login logic goes here
                println("Email: $email, Password: $password")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Log In")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("or")

        Spacer(modifier = Modifier.height(16.dp))

        // --- 5. Google Sign-In Button ---
        OutlinedButton(
            onClick = {
                scope.launch {
                    googleAuthViewModel.signInWithGoogle(
                        context = navController.context
                    )
                }

                // Google sign-in logic goes here
                //println("Google Sign-In clicked!")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sign In with Google")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- 6. Bottom Links ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween // Puts links on opposite ends
        ) {
            TextButton(
                onClick = {
                    // Forgot password logic
                    println("Forgot Password clicked!")
                }
            ) {
                Text("Forgot Password?")
            }

            TextButton(
                onClick = {
                    // Sign up logic
                    println("Sign Up clicked!")
                }
            ) {
                Text("Sign Up")
            }
        }
    }
}