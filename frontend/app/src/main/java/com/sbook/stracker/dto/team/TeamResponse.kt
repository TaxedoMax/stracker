package com.sbook.stracker.dto.team

import kotlinx.serialization.Serializable

@Serializable
data class TeamResponse(
    val id: Long,
    val name: String,
    val isUserLead: Boolean
)