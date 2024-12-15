package com.sbook.stracker.dto.team

import kotlinx.serialization.Serializable

@Serializable
data class TeamResponseDTO(
    val id: Long,
    val name: String,
    val isOwner: Boolean
)