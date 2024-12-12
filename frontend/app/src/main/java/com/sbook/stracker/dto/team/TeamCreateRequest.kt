package com.sbook.stracker.dto.team

data class TeamCreateRequest(
    val name: String,
    val adminId: String,
    val usersIdsList: List<String>
)