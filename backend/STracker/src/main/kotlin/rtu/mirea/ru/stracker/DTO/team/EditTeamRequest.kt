package rtu.mirea.ru.stracker.DTO.team

data class EditTeamRequest (
    val userId: Long,
    val teamId: Long,
    val teamName: String,
)