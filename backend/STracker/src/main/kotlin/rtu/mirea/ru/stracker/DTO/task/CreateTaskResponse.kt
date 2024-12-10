package rtu.mirea.ru.stracker.DTO.task

data class CreateTaskResponse(
    val id: Long,
    val name: String,
    val status: String,
    val type: String,
    val description: String?,
    val authorId: Long,
    val executorLogin: String?,
    val teamId: Long,
)
