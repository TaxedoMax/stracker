package com.sbook.stracker.dto.team

data class EditTeamDTO(
    val id: String,
    val name: String,
    val usersIdsList: List<String>
)