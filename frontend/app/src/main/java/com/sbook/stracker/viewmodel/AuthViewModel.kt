package com.sbook.stracker.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
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

    fun loginUser(onSuccess: () -> Unit, onError: (String) -> Unit) {
        val user = userRepository.getUserByLogin(login)
        if (user != null && user.password == password) {
            onSuccess()
        } else {
            errorMessage = "Неверный логин или пароль"
            onError(errorMessage)
        }
    }

    fun registerUser(onSuccess: () -> Unit, onError: (String) -> Unit) {
        if (userRepository.getUserByLogin(login) != null) {
            errorMessage = "Пользователь уже существует"
            onError(errorMessage)
        } else {
            val newUser = User(id = "1", login = login, password = password)
            userRepository.addUser(newUser)
            onSuccess()
        }
    }
}
