package com.sbook.stracker.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.sbook.stracker.entity.User
import com.sbook.stracker.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    var login by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var errorMessage by mutableStateOf("")
        private set

    fun onLoginChanged(newLogin: String) {
        login = newLogin
    }

    fun onPasswordChanged(newPassword: String) {
        password = newPassword
    }

    fun loginUser(userViewModel: UserViewModel, navController: NavHostController) {
        val user = User(id = "-1", login = login, password = password)

        val userId = userRepository.login(user)
        if (userId != "-1") {
            userViewModel.setUserId(userId)
            navController.navigate("teams")
        } else {
            errorMessage = "Неверный логин или пароль"
        }
    }

    fun registerUser(userViewModel: UserViewModel, navController: NavHostController) {
        val newUser = User(id = "-1", login = login, password = password)
        val userId = userRepository.registerUser(newUser)

        if (userId != "-1") {
            userViewModel.setUserId(userId)
            navController.navigate("teams")
        } else {
            errorMessage = "Пользователь уже существует"
        }
    }
}
