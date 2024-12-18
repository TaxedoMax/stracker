package com.sbook.stracker.repository

import com.sbook.stracker.dto.TaskDTO
import com.sbook.stracker.entity.Task

interface TaskRepository {
    suspend fun getTaskById(id: Long): Task?
    suspend fun addTask(task: TaskDTO): Boolean
    suspend fun updateTask(task: Task): Boolean
    suspend fun deleteTask(id: Long): Boolean
    suspend fun getTasksByUserId(userId: Long): List<Task>
    suspend fun getTasksByTeamId(teamId: Long): List<Task>
}
