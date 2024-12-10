package com.sbook.stracker.dto.team

data class CreateTeamDTO(
    val name: String,
    val adminId: String,
    val usersIdsList: List<String>
)