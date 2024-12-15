package com.sbook.stracker.dto.team

data class TeamEditRequest(
    val id: Long,
    val name: String,
    val usersIdsList: List<Long>
)