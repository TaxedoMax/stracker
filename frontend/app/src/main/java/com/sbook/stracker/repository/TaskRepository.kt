package com.sbook.stracker.repository

import com.sbook.stracker.dto.TaskDTO
import com.sbook.stracker.entity.Task

interface TaskRepository {
    suspend fun getTaskById(id: String): Task?
    suspend fun addTask(task: TaskDTO): Boolean
    suspend fun updateTask(task: Task): Boolean
    suspend fun deleteTask(id: String): Boolean
    suspend fun getTasksByUserId(userId: String): List<Task>
    suspend fun getTasksByTeamId(teamId: String): List<Task>
}
