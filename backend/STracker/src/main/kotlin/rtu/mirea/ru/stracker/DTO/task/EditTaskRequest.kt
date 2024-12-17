package rtu.mirea.ru.stracker.DTO.task

import rtu.mirea.ru.stracker.entity.TaskStatus
import rtu.mirea.ru.stracker.entity.TaskType

data class EditTaskRequest(
    val editorId: Long,
    val taskId: Long,
    val name: String,
    val status: TaskStatus,
    val type: TaskType,
    val description: String?,
    val executorLogin: String?,
)
