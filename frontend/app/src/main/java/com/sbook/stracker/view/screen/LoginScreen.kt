package com.sbook.stracker.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sbook.stracker.R
import com.sbook.stracker.ui.theme.STrackerTheme
import com.sbook.stracker.viewmodel.AuthViewModel
import com.sbook.stracker.viewmodel.UserViewModel

@Composable
fun LoginScreen(
    navController: NavHostController,
    userViewModel: UserViewModel
) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val login = authViewModel.login
    val password = authViewModel.password
    val errorMessage = authViewModel.errorMessage

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            alignment = Alignment.Center,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Вход", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = login,
            onValueChange = { authViewModel.onLoginChanged(it) },
            label = { Text("Логин") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { authViewModel.onPasswordChanged(it) },
            label = { Text("Пароль") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { authViewModel.loginUser(userViewModel, navController) }) {
            Text(text = "Войти")
        }

        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = errorMessage, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = { navController.navigate("register") }) {
            Text(text = "Нет аккаунта? Зарегистрироваться")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen(){
    STrackerTheme {
        LoginScreen(navController = rememberNavController(), hiltViewModel())
    }
}
