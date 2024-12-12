package com.sbook.stracker.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.sbook.stracker.dto.TaskDTO
import com.sbook.stracker.dto.team.TeamResponseDTO
import com.sbook.stracker.dto.user.UserDTO
import com.sbook.stracker.entity.TaskStatus
import com.sbook.stracker.entity.TaskType
import com.sbook.stracker.entity.Team
import com.sbook.stracker.repository.TaskRepository
import com.sbook.stracker.repository.TeamRepository
import com.sbook.stracker.repository.UserRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class TaskViewModel @AssistedInject constructor(
    @Assisted
    private val teamInit: TeamResponseDTO?,
    @Assisted("userId")
    private val userId: String,
    @Assisted("taskId")
    private val taskId: String?,
    private val taskRepository: TaskRepository,
    private val userRepository: UserRepository,
    private val teamRepository: TeamRepository,
) : ViewModel() {
    val isNewTask: Boolean get() = taskId == null

    val isLoading = mutableStateOf(false)

    val task = mutableStateOf(TaskDTO())
    val owner = mutableStateOf<UserDTO?>(null)
    val executor = mutableStateOf<UserDTO?>(null)
    val team = mutableStateOf<Team?>(null)

    val isTypeExpanded = mutableStateOf(false)
    val isStatusExpanded = mutableStateOf(false)

    init{
        loadData()
    }

    private fun loadData(){
        viewModelScope.launch {
            isLoading.value = true

            // Старая таска
            if(taskId != null){
                val currentTask = taskRepository.getTaskById(taskId)
                task.value = currentTask!!.toTaskDTO()
                team.value = teamRepository.getTeamById(task.value.teamId)
                owner.value = userRepository.getUserById(task.value.ownerId)
                executor.value = userRepository.getUserById(task.value.executorId ?: "")
            }
            // Новая таска
            else if(teamInit != null){
                task.value = task.value.copy(
                    teamId = teamInit.id,
                    ownerId = userId,
                )
            }
            // Не хватает инфы, какая-то ошибка
            else{
                // TODO(Error)
            }

            isLoading.value = false
        }
    }
    fun onTitleChanged(newTitle: String){
        task.value = task.value.copy(title = newTitle)
    }
    fun onDescriptionChanged(newDescription: String){
        task.value = task.value.copy(description = newDescription)
    }
    fun onTypeChanged(newType: TaskType){
        task.value = task.value.copy(type = newType)
    }

    fun onStatusChanged(newStatus: TaskStatus){
        task.value = task.value.copy(status = newStatus)
    }

    fun createTask(navigateBack: () -> Unit){
        viewModelScope.launch {
            isLoading.value = true
            if(task.value.title.isNotEmpty() && task.value.description.isNotEmpty()){
                taskRepository.addTask(task.value)
                navigateBack()
            } else {
                // TODO
            }
            isLoading.value = false
        }
    }
    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted
            teamInit: TeamResponseDTO? = null,
            @Assisted("userId")
            userId: String,
            @Assisted("taskId")
            taskId: String? = null,
        ): TaskViewModel
    }
    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            teamInit: TeamResponseDTO?,
            userId: String,
            taskId: String?,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(teamInit, userId, taskId) as T
            }
        }
    }
}