package com.sbook.stracker.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sbook.stracker.dto.team.NewTeamDTO
import com.sbook.stracker.dto.team.TeamEditDTO
import com.sbook.stracker.dto.team.GetTeamByIdRequest
import com.sbook.stracker.dto.user.UserDTO
import com.sbook.stracker.repository.TeamRepository
import com.sbook.stracker.repository.UserRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class TeamEditViewModel @AssistedInject constructor(
    private val teamRepository: TeamRepository,
    private val userRepository: UserRepository,
    @Assisted("userId")
    val userId: Long,
    @Assisted("teamId")
    val teamId: Long?,
) : ViewModel(){
    val name = mutableStateOf("")

    val usersList = mutableStateOf<List<UserDTO>>(mutableListOf())
    val newUserLogin = mutableStateOf("")

    val dialogText = mutableStateOf("")
    val isDialogOpened = mutableStateOf(false)

    val isDataLoading = mutableStateOf(false)
    val isUserLoading = mutableStateOf(false)

    val isSomethingLoading get() = run {
        isDataLoading.value && isUserLoading.value
    }

    init{
        if(teamId == null) initFirstUser() else loadData()
    }
    private fun loadData(){
        viewModelScope.launch {
            isDataLoading.value = true

            val team = teamRepository.getTeamById(
                GetTeamByIdRequest(
                    userId = userId,
                    teamId = teamId!!
                )
            )

            name.value = team!!.name

            usersList.value = userRepository.getUsersByTeam(teamId)

            isDataLoading.value = false
        }
    }

    private fun initFirstUser(){
        viewModelScope.launch {
            isDataLoading.value = true
            // TODO: Some exception if bad inet
            val user = userRepository.getUserById(userId)!!
            usersList.value = listOf(user)
            isDataLoading.value = false
        }
    }

    fun onNewUserLoginChanged(newLogin: String){
        newUserLogin.value = newLogin
    }
    fun onNameChanged(newName: String){
        name.value = newName
    }

    fun closeDialog(){
        isDialogOpened.value = false
        dialogText.value = ""
    }
    fun addUser(){
        viewModelScope.launch {
            isUserLoading.value = false

            val user = userRepository.getUserByLogin(newUserLogin.value)

            if(user != null){
                if(usersList.value.indexOfFirst { it.login == user.login } != -1){
                    dialogText.value = "Пользователь уже добавлен!"
                    isDialogOpened.value = true
                } else{
                    usersList.value = usersList.value.plus(user)
                }
            } else{
                dialogText.value = "Пользователя с таким ником не существует!"
                isDialogOpened.value = true
            }
            isUserLoading.value = true
        }
    }

    fun removeUser(index: Int){
        usersList.value = usersList.value.minus(usersList.value[index])
    }

    fun onConfirmButtonClick(navigateBack: () -> Unit){
        if(teamId == null) createTeam(navigateBack) else editTeam(navigateBack)
    }
    private fun createTeam(navigateBack: () -> Unit){
        viewModelScope.launch {
            isDataLoading.value = true

            if(name.value.isNotEmpty()){
                val teamDTO = NewTeamDTO(
                    name = name.value,
                    adminId = userId,
                    usersIdsList = usersList.value.map{ it.id }
                )
                teamRepository.createTeam(teamDTO)
                navigateBack()
            } else{
                dialogText.value = "Название не должно быть пустым!"
                isDialogOpened.value = true
            }

            isDataLoading.value = false
        }
    }

    private fun editTeam(navigateBack: () -> Unit){
        viewModelScope.launch {
            isDataLoading.value = true

            if(name.value.isNotEmpty()){
                val teamDTO = TeamEditDTO(
                    id = teamId!!,
                    name = name.value,
                    usersIdsList = usersList.value.map{ it.id }
                )
                teamRepository.updateTeam(teamDTO)
                navigateBack()
            } else{
                dialogText.value = "Название не должно быть пустым!"
                isDialogOpened.value = true
            }

            isDataLoading.value = false
        }
    }
    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("userId")
            userId: Long,
            @Assisted("teamId")
            teamId: Long?,
            ): TeamEditViewModel
    }
    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            userId: Long,
            teamId: Long?,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(userId, teamId) as T
            }
        }
    }
}