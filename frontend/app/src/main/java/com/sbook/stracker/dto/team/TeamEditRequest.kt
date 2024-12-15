package com.sbook.stracker.dto.team

data class TeamEditRequest(
    val id: String,
    val name: String,
    val usersIdsList: List<String>
)