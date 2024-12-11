package com.sbook.stracker.dto.team

import kotlinx.serialization.Serializable

@Serializable
data class TeamForUserDTO(
    val id: String,
    val name: String,
    val isOwner: Boolean
)