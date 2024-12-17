package com.sbook.stracker.entity

import com.sbook.stracker.dto.task.TaskDTO
import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: Long,
    val teamId: Long,
    val authorId: Long,
    val executorId: Long?,
    val name: String,
    val description: String,
    val status: TaskStatus,
    val type: TaskType,
){
    fun toTaskDTO(): TaskDTO {
        return TaskDTO(
            teamId = teamId,
            authorId = authorId,
            executorId = executorId,
            name = name,
            description = description,
            status = status,
            type = type,
        )
    }
}

enum class TaskStatus(val text: String){
    OPEN("Открыта"),
    CLOSE("Закрыта"),
    IN_PROGRESS("В работе"),
    NEED_INFORMATION("Требуется информация"),
    ON_CHECK("На проверке"),
}

enum class TaskType(val text: String){
    HANDMADE("Рукоделие"),
    BUYING("Покупка"),
    CREATION("Создание"),
    TASK("Задача"),
}