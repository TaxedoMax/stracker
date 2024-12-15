package com.sbook.stracker.dto.team

data class TeamCreateRequest(
    val name: String,
    val adminId: Long,
    val usersIdsList: List<Long>
)