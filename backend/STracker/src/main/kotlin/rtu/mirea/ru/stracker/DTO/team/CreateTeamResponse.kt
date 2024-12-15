package rtu.mirea.ru.stracker.DTO.team

import rtu.mirea.ru.stracker.entity.TeamStatus

data class CreateTeamResponse(
    val id: Long,
    val name: String,
    val photo: String?,
    val leadId: Long,
    val description: String?,
    val status: TeamStatus,
)
