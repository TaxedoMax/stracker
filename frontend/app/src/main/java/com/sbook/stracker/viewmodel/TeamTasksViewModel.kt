package com.sbook.stracker.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sbook.stracker.dto.team.GetTeamByIdResponse
import com.sbook.stracker.entity.Task
import com.sbook.stracker.repository.TaskRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class TeamTasksViewModel @AssistedInject constructor(
    @Assisted
    val userId: Long,
    @Assisted
    val team: GetTeamByIdResponse,
    private val taskRepository: TaskRepository
): ViewModel(){
    val tasksList = mutableStateOf<List<Task>>(emptyList())
    val isDataLoading = mutableStateOf(false)

    init{
        loadData()
    }

    private fun loadData(){
        viewModelScope.launch {
            isDataLoading.value = true
            tasksList.value = taskRepository.getTasksByTeamId(team.id)
            isDataLoading.value = false
        }
    }
    @AssistedFactory
    interface Factory{
        fun create(userId: Long, team: GetTeamByIdResponse): TeamTasksViewModel
    }
    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: TeamTasksViewModel.Factory,
            userId: Long,
            team: GetTeamByIdResponse
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(userId, team) as T
            }
        }
    }
}