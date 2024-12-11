package com.sbook.stracker.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbook.stracker.entity.Task
import com.sbook.stracker.entity.User
import com.sbook.stracker.repository.TaskRepository
import com.sbook.stracker.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val taskRepository: TaskRepository
) : ViewModel() {

    private var _userId: String = "-1"
    val userId: String get() = _userId

    val user = mutableStateOf<User?>(null)
    val userTasks = mutableStateOf<List<Task>>(emptyList())

    val isDataLoading = mutableStateOf(false)


    fun setUserId(id: String){
        _userId = id
        loadUser(id)
        loadUserTasks(id)
    }

    private fun loadUser(userId: String) {
        viewModelScope.launch {
            isDataLoading.value = true
            user.value = userRepository.getUserById(userId)
            isDataLoading.value = false
        }
    }

    private fun loadUserTasks(userId: String) {
        viewModelScope.launch {
            isDataLoading.value = true
            userTasks.value = taskRepository.getTasksByUserId(userId)
            isDataLoading.value = false
        }
    }
}
