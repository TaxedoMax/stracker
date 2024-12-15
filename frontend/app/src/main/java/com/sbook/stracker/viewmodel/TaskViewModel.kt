package com.sbook.stracker.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sbook.stracker.dto.TaskDTO
import com.sbook.stracker.dto.team.TeamGetRequest
import com.sbook.stracker.dto.team.TeamResponse
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
    private val teamInit: TeamResponse?,
    @Assisted("userId")
    val userId: Long,
    @Assisted("taskId")
    private val taskId: Long?,
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
                try{
                    val currentTask = taskRepository.getTaskById(taskId)
                    task.value = currentTask!!.toTaskDTO()
                    team.value = teamRepository.getTeamById(
                        TeamGetRequest(
                            userId = userId,
                            teamId = task.value.teamId
                        )
                    )
                    owner.value = userRepository.getUserById(task.value.ownerId)
                    if(task.value.executorId != null){
                        executor.value = userRepository.getUserById(task.value.executorId!!)
                    } else{
                        executor.value = null
                    }
                } catch (e: Exception){
                    println("TaskViewModelInitError: ${e.message}")
                }
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
                TODO()
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

    fun onAcceptPressed(navigateBack: () -> Unit){
        if(taskId == null) createTask(navigateBack) else updateTask(navigateBack)
    }
    private fun createTask(navigateBack: () -> Unit){
        viewModelScope.launch {
            isLoading.value = true
            if(task.value.title.isNotEmpty() && task.value.description.isNotEmpty()){
                taskRepository.addTask(task.value)
                navigateBack()
            } else {
                TODO()
            }
            isLoading.value = false
        }
    }
    fun setExecutor(){
        viewModelScope.launch{
            isLoading.value = true
            task.value = task.value.copy(executorId = userId)
            taskRepository.updateTask(task.value.toTask(taskId!!))
            loadData()
            isLoading.value = false
        }
    }
    fun removeExecutor(){
        viewModelScope.launch{
            isLoading.value = true
            task.value = task.value.copy(executorId = null)
            taskRepository.updateTask(task.value.toTask(taskId!!))
            loadData()
            isLoading.value = false
        }
    }
    private fun updateTask(navigateBack: () -> Unit) {
        viewModelScope.launch {
            isLoading.value = true
            if(task.value.title.isNotEmpty() && task.value.description.isNotEmpty()){
                taskRepository.updateTask(task.value.toTask(taskId!!))
                navigateBack()
            } else{
                TODO()
            }
            isLoading.value = false
        }
    }
    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted
            teamInit: TeamResponse? = null,
            @Assisted("userId")
            userId: Long,
            @Assisted("taskId")
            taskId: Long? = null,
        ): TaskViewModel
    }
    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            teamInit: TeamResponse?,
            userId: Long,
            taskId: Long?,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(teamInit, userId, taskId) as T
            }
        }
    }
}