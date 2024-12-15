package rtu.mirea.ru.stracker.DTO.team

data class AddUserRequest(
    val leadId: Long,
    val userLogin: String,
    val teamId: Long,
)