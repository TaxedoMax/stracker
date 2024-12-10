package rtu.mirea.ru.stracker.DTO.team

import rtu.mirea.ru.stracker.entity.TeamStatus

data class GetTeamResponse (
    val id: Long,
    val name: String,
    val photo: String?,
    val leadId: Long,
    val description: String?,
    val status: TeamStatus,
    val tasks: List<TaskInTeam>,
    val isUserLead: Boolean,
)

data class TaskInTeam (
    val id: Long,
    val name: String,
    val status: String,
    val type: String,
    val description: String?,
    val authorId: Long,
    val executorLogin: String?,
    val teamId: Long,
)