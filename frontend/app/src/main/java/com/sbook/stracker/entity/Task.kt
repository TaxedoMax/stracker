package com.sbook.stracker.entity

import com.sbook.stracker.dto.TaskDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: Long,
    val teamId: Long,
    val authorId: Long,
    val executorId: Long?,
    @SerialName("name")
    val title: String,
    val description: String,
    val status: TaskStatus,
    val type: TaskType,
){
    fun toTaskDTO(): TaskDTO {
        return TaskDTO(
            teamId = teamId,
            authorId = authorId,
            executorId = executorId,
            title = title,
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
    HANDMADE("Не придумал"),
    BUYING("Покупка"),
    CREATION("Создание"),
    TASK("Задача"),
}