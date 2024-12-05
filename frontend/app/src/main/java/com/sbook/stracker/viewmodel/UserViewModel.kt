package com.sbook.stracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbook.stracker.entity.Task
import com.sbook.stracker.entity.User
import com.sbook.stracker.repository.TaskRepository
import com.sbook.stracker.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val taskRepository: TaskRepository
) : ViewModel() {

    private var _userId: String = "-1"
    val userId: String get() = _userId

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> get() = _user

    private val _userTasks = MutableStateFlow<List<Task>>(emptyList())
    val userTasks: StateFlow<List<Task>> get() = _userTasks

    fun setUserId(id: String){
        _userId = id
    }

//    fun loadUser(userId: String) {
//        viewModelScope.launch {
//            _user.value = userRepository.getUserById(userId)
//        }
//    }
//
//    fun loadUserTasks(userId: String) {
//        viewModelScope.launch {
//            _userTasks.value = taskRepository.getTasksByUserId(userId)
//        }
//    }
//
    fun getUser(): User? {
        return userRepository.getUserById(userId)
    }

    fun getUserTasks(): List<Task> {
        return taskRepository.getTasksByUserId(userId)
    }
}
