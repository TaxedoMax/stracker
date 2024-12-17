package com.sbook.stracker.dto.team

data class NewTeamDTO(
    val name: String,
    val adminId: Long,
    val usersIdsList: List<Long>
)