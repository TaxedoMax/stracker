package com.sbook.stracker.dto

import com.sbook.stracker.entity.TaskStatus
import com.sbook.stracker.entity.TaskType

data class TaskDTO(
    val teamId: String = "",
    val ownerId: String = "",
    val executorId: String? = null,
    val title: String = "",
    val description: String = "",
    val status: TaskStatus = TaskStatus.OPEN,
    val type: TaskType = TaskType.TASK,
)