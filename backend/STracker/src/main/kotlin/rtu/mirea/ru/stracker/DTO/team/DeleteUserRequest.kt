package rtu.mirea.ru.stracker.DTO.team

data class DeleteUserRequest(
    val userId: Long,
    val userLoginToDelete: String,
    val teamId: Long,
)
