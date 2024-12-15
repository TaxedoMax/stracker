package rtu.mirea.ru.stracker.DTO.team

data class GetUsersResponse(
    val users: List<UserInTeam>,
)

data class UserInTeam(
    val login: String,
    val id: Long,
    val photo: String?,
    val isLead: Boolean,
)