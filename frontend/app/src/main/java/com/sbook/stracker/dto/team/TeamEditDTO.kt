package com.sbook.stracker.dto.team

data class TeamEditDTO(
    val id: Long,
    val name: String,
    val usersIdsList: List<Long>
)