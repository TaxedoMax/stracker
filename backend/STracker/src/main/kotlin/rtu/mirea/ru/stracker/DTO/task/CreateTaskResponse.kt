package rtu.mirea.ru.stracker.DTO.task

import rtu.mirea.ru.stracker.entity.TaskStatus
import rtu.mirea.ru.stracker.entity.TaskType

data class CreateTaskResponse(
    val id: Long,
    val name: String,
    val status: TaskStatus,
    val type: TaskType,
    val description: String?,
    val authorId: Long,
    val executorLogin: String?,
    val teamId: Long,
)
