package com.sbook.stracker.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sbook.stracker.dto.team.GetTeamByIdResponse
import com.sbook.stracker.repository.TeamRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class TeamsViewModel @AssistedInject constructor(
    private val teamRepository: TeamRepository,
    @Assisted
    private val userId: Long,
) : ViewModel() {

    private val _isDataLoading = mutableStateOf(false)
    private val _userTeams = mutableStateOf<List<GetTeamByIdResponse>>(emptyList())
    val userTeams get() = _userTeams
    val isDataLoading get() = _isDataLoading

    init{
        loadTeams()
    }
    private fun loadTeams(){
        viewModelScope.launch {
            _isDataLoading.value = true
            _userTeams.value = teamRepository.getTeamsByUserId(userId)
            _isDataLoading.value = false
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(userId: Long): TeamsViewModel
    }
    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            userId: Long
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(userId) as T
            }
        }
    }
}