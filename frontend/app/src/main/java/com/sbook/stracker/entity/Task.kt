package com.sbook.stracker.entity

import com.sbook.stracker.dto.TaskDTO

data class Task(
    val id: Long,
    val teamId: Long,
    val ownerId: Long,
    val executorId: Long?,
    val title: String,
    val description: String,
    val status: TaskStatus,
    val type: TaskType,
){
    fun toTaskDTO(): TaskDTO {
        return TaskDTO(
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