package com.sbook.stracker.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sbook.stracker.dto.team.TeamForUserDTO
import com.sbook.stracker.entity.Team
import com.sbook.stracker.repository.TeamRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class TeamViewModel @AssistedInject constructor(
    private val teamRepository: TeamRepository,
    @Assisted
    private val userId: String,
) : ViewModel() {

    private val _isDataLoading = mutableStateOf(false)
    private val _userTeams = mutableStateOf<List<TeamForUserDTO>>(emptyList())
    val userTeams get() = _userTeams
    val isDataLoading get() = _isDataLoading

    init{
        loadTeams()
    }
    fun loadTeams(){
        viewModelScope.launch {
            _isDataLoading.value = true
            _userTeams.value = teamRepository.getTeamsByUserId(userId)
            _isDataLoading.value = false
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(userId: String): TeamViewModel
    }
    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            userId: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(userId) as T
            }
        }
    }
}