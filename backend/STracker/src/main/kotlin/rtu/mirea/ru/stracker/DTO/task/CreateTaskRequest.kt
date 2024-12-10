package rtu.mirea.ru.stracker.DTO.task

data class CreateTaskRequest(
    val name: String,
    val type: String,
    val description: String?,
    val authorId: Long,
    val executorLogin: String?,
    val teamId: Long,
)
