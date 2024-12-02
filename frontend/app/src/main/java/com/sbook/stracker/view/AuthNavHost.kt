package com.sbook.stracker.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.sbook.stracker.view.screen.LoginScreen
import com.sbook.stracker.view.screen.RegistrationScreen
import com.sbook.stracker.viewmodel.AuthViewModel

@Composable
fun AuthNavHost(
    onAuthSuccess: () -> Unit
) {
    var isLoginScreen by remember { mutableStateOf(true) }

    if (isLoginScreen) {
        LoginScreen(
            onRegisterClick = { isLoginScreen = false },
            onLoginSuccess = onAuthSuccess
        )
    } else {
        RegistrationScreen(
            onLoginClick = { isLoginScreen = true },
            onRegisterSuccess = onAuthSuccess
        )
    }
}