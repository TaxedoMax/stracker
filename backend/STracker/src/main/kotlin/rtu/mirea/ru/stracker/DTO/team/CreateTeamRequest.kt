package rtu.mirea.ru.stracker.DTO.team

data class CreateTeamRequest(
    val name: String,
    val photo: String?,
    val leadId: Long,
    val description: String?,
)
