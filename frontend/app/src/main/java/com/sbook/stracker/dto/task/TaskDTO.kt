package com.sbook.stracker.dto.task

import com.sbook.stracker.entity.Task
import com.sbook.stracker.entity.TaskStatus
import com.sbook.stracker.entity.TaskType

data class TaskDTO(
    val teamId: Long = -1,
    val authorId: Long = -1,
    val executorId: Long? = null,
    val name: String = "",
    val description: String = "",
    val status: TaskStatus = TaskStatus.OPEN,
    val type: TaskType = TaskType.TASK,
){
    fun toTask(id: Long) : Task {
        return Task(
            id = id,
            teamId = teamId,
            authorId = authorId,
            executorId = executorId,
            name = name,
            description = description,
            status = status,
            type = type,
        )
    }

    fun toTaskUpdateRequest(id: Long, editorId: Long, executorLogin: String?) : TaskUpdateRequest{
        return TaskUpdateRequest(
            taskId = id,
            editorId = editorId,
            name = name,
            status = status,
            type = type,
            description = description,
            executorLogin = executorLogin,
        )
    }
}