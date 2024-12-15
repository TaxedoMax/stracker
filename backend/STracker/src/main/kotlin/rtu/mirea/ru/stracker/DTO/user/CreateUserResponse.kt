package rtu.mirea.ru.stracker.DTO.user

data class CreateUserResponse(
    val id: Long,
    val login: String,
    val photo: String?,
)
