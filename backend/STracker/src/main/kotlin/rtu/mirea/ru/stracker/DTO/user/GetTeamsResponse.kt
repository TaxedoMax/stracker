package rtu.mirea.ru.stracker.DTO.user

data class GetTeamsResponse (
    val teams: List<TeamsPreview>,
)

data class TeamsPreview(
    val id: Long,
    val name: String,
    val description: String?,
    val photo: String?,
    val isLeader: Boolean,
)