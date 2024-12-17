package com.sbook.stracker.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbook.stracker.dto.user.UserDTO
import com.sbook.stracker.entity.Task
import com.sbook.stracker.entity.User
import com.sbook.stracker.repository.TaskRepository
import com.sbook.stracker.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val taskRepository: TaskRepository
) : ViewModel() {

    private var _userId: Long = -1
    val userId: Long get() = _userId

    val user = mutableStateOf<UserDTO?>(null)
    val userTasks = mutableStateOf<List<Task>>(emptyList())

    val isDataLoading = mutableStateOf(false)


    fun setUserId(id: Long){
        _userId = id
        loadUser()
        loadUserTasks()
    }

    private fun loadUser() {
        viewModelScope.launch {
            isDataLoading.value = true
            user.value = userRepository.getUserById(userId)
            isDataLoading.value = false
        }
    }

    fun loadUserTasks() {
        viewModelScope.launch {
            isDataLoading.value = true
            userTasks.value = taskRepository.getTasksByUserId(userId)
            isDataLoading.value = false
        }
    }
}
