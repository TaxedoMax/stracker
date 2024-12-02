package com.sbook.stracker.view.screen

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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sbook.stracker.repository.mock.InMemoryUserRepository
import com.sbook.stracker.ui.theme.STrackerTheme
import com.sbook.stracker.viewmodel.AuthViewModel

@Composable
fun RegistrationScreen(
    onLoginClick: () -> Unit,
    onRegisterSuccess: () -> Unit
) {
    val viewModel : AuthViewModel = hiltViewModel()
    val login = viewModel.login
    val password = viewModel.password
    val errorMessage = viewModel.errorMessage

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Логотип", fontSize = 32.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Регистрация", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = login,
            onValueChange = { viewModel.onLoginChanged(it) },
            label = { Text("Логин") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = password,
            onValueChange = { viewModel.onPasswordChanged(it) },
            label = { Text("Пароль") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.registerUser(onRegisterSuccess, { viewModel.errorMessage }) }) {
            Text(text = "Зарегистрироваться")
        }

        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = errorMessage, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = onLoginClick) {
            Text(text = "Уже есть аккаунт? Войти")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen(){
    STrackerTheme {
        RegistrationScreen(
            //viewModel = AuthViewModel(userRepository = InMemoryUserRepository()),
            onLoginClick = { /*TODO*/ }
        ) {}
    }
}