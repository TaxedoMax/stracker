package com.sbook.stracker.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbook.stracker.dto.user.AuthRequest
import com.sbook.stracker.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

    fun loginUser(userViewModel: UserViewModel, navigateTo: (route: String) -> Unit) {
        viewModelScope.launch {
            val authDTO = AuthRequest(login = login, password = password)

            val userId = userRepository.login(authDTO)
            if (userId != "-1") {
                userViewModel.setUserId(userId)
                navigateTo("teams")
            } else {
                errorMessage = "Неверный логин или пароль"
            }
        }
    }

    fun registerUser(userViewModel: UserViewModel, navigateTo: (route: String) -> Unit) {
        viewModelScope.launch {
            val authDTO = AuthRequest(login = login, password = password)
            val userId = userRepository.register(authDTO)

            if (userId != "-1") {
                userViewModel.setUserId(userId)
                navigateTo("teams")
            } else {
                errorMessage = "Пользователь уже существует"
            }
        }
    }
}
