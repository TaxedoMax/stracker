package com.sbook.stracker.entity

import com.sbook.stracker.dto.TaskDTO

data class Task(
    val id: String,
    val teamId: String,
    val ownerId: String,
    val executorId: String?,
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
    OPEN("Открытая"),
    CLOSE("Закрытая"),
    IN_PROGRESS("В процессе"),
    NEED_INFORMATION("Требуется информация"),
    ON_CHECK("На проверке"),
}

enum class TaskType(val text: String){
    HANDMADE("Хз, рукодельная"),
    BUYING("Покупка"),
    CREATION("Сотворение"),
    TASK("Задача"),
}