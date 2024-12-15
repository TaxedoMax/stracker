package com.sbook.stracker.repository.impl

import com.sbook.stracker.api.RetrofitClient
import com.sbook.stracker.dto.TaskDTO
import com.sbook.stracker.entity.Task
import com.sbook.stracker.repository.TaskRepository

class TaskRepositoryImpl : TaskRepository{
    private val api = RetrofitClient.taskApiService
    override suspend fun getTaskById(id: String): Task? {
        TODO("Not yet implemented")
    }

    override suspend fun addTask(task: TaskDTO): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun updateTask(task: Task): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(id: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getTasksByUserId(userId: String): List<Task> {
        TODO("Not yet implemented")
    }

    override suspend fun getTasksByTeamId(teamId: String): List<Task> {
        TODO("Not yet implemented")
    }

}