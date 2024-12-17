package com.sbook.stracker.dto.task

import com.sbook.stracker.entity.TaskStatus
import com.sbook.stracker.entity.TaskType

data class TaskUpdateRequest(
    val taskId: Long = -1L,
    val editorId: Long = -1L,
    val name: String = "",
    val status: TaskStatus = TaskStatus.OPEN,
    val type: TaskType = TaskType.TASK,
    val description: String = "",
    val executorLogin: String? = null
)