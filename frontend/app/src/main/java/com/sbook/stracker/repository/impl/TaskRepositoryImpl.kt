package com.sbook.stracker.repository.impl

import com.sbook.stracker.api.RetrofitClient
import com.sbook.stracker.dto.TaskDTO
import com.sbook.stracker.entity.Task
import com.sbook.stracker.repository.TaskRepository

class TaskRepositoryImpl : TaskRepository{
    private val api = RetrofitClient.taskApiService
    override suspend fun getTaskById(id: Long): Task? {
        var response: Task? = null

        try{
            val request = api.getTaskById(id).execute()
            if(request.isSuccessful){
                response = request.body()
            }
        } catch (ex: Exception){
            response = null
        }

        return response
    }

    override suspend fun getTasksByUserId(userId: Long): List<Task> {
        var response = emptyList<Task>()

        try{
            val request = api.getTasksByUserId(userId).execute()
            if(request.isSuccessful){
                response = request.body() ?: emptyList()
            }
        } catch (ex: Exception){
            response = emptyList()
        }

        return response
    }

    override suspend fun getTasksByTeamId(teamId: Long): List<Task> {
        var response = emptyList<Task>()

        try{
            val request = api.getTasksByTeamId(teamId).execute()
            if(request.isSuccessful){
                response = request.body() ?: emptyList()
            }
        } catch (ex: Exception){
            response = emptyList()
        }

        return response
    }

    override suspend fun addTask(task: TaskDTO): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun updateTask(task: Task): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(id: Long): Boolean {
        TODO("Not yet implemented")
    }
}