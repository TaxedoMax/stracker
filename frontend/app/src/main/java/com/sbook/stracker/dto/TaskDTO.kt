package com.sbook.stracker.dto

import com.sbook.stracker.entity.Task
import com.sbook.stracker.entity.TaskStatus
import com.sbook.stracker.entity.TaskType

data class TaskDTO(
    val teamId: Long = -1,
    val ownerId: Long = -1,
    val executorId: Long? = null,
    val title: String = "",
    val description: String = "",
    val status: TaskStatus = TaskStatus.OPEN,
    val type: TaskType = TaskType.TASK,
){
    fun toTask(id: Long) : Task {
        return Task(
            id = id,
            teamId = teamId,
            ownerId = ownerId,
            executorId = executorId,
            title = title,
            description = description,
            status = status,
            type = type,
        )
    }
}