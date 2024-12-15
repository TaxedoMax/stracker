package rtu.mirea.ru.stracker.DTO.task

import rtu.mirea.ru.stracker.entity.TaskType

data class CreateTaskRequest(
    val name: String,
    val type: TaskType,
    val description: String?,
    val authorId: Long,
    val executorLogin: String?,
    val teamId: Long,
)
