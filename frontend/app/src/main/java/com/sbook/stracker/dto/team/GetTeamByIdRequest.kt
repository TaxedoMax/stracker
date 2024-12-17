package com.sbook.stracker.dto.team

data class GetTeamByIdRequest(
    val userId: Long,
    val teamId: Long,
)