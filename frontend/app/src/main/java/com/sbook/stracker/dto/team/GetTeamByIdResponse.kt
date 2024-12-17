package com.sbook.stracker.dto.team

import kotlinx.serialization.Serializable

@Serializable
data class GetTeamByIdResponse(
    val id: Long,
    val name: String,
    val isUserLead: Boolean
)