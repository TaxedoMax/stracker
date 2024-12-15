package com.sbook.stracker.repository.impl

import com.sbook.stracker.api.RetrofitClient
import com.sbook.stracker.dto.TaskDTO
import com.sbook.stracker.entity.Task
import com.sbook.stracker.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskRepositoryImpl : TaskRepository{
    private val api = RetrofitClient.taskApiService
    override suspend fun getTaskById(id: Long): Task? {
        return withContext(Dispatchers.IO){
            var response: Task? = null

            try{
                val request = api.getTaskById(id).execute()
                if(request.isSuccessful){
                    response = request.body()
                }
            } catch (ex: Exception){
                response = null
            }

            response
        }
    }

    override suspend fun getTasksByUserId(userId: Long): List<Task> {
        return withContext(Dispatchers.IO){
            var response = emptyList<Task>()

            try{
                val request = api.getTasksByUserId(userId).execute()
                if(request.isSuccessful){
                    response = request.body() ?: emptyList()
                }
            } catch (ex: Exception){
                response = emptyList()
            }

            response
        }
    }

    override suspend fun getTasksByTeamId(teamId: Long): List<Task> {
        return withContext(Dispatchers.IO){
            var response = emptyList<Task>()

            try{
                val request = api.getTasksByTeamId(teamId).execute()
                if(request.isSuccessful){
                    response = request.body() ?: emptyList()
                }
            } catch (ex: Exception){
                response = emptyList()
            }

            response
        }
    }

    override suspend fun addTask(task: TaskDTO): Boolean {
        return withContext(Dispatchers.IO){
            var response = false
            try {
                val request = api.createTask(task).execute()
                if(request.isSuccessful){
                    response = request.body() ?: false
                }
            } catch (ex: Exception){
                response = false
            }
            response
        }
    }

    override suspend fun updateTask(task: Task): Boolean {
        return withContext(Dispatchers.IO){
            var response = false
            try {
                val request = api.createTask(task.toTaskDTO()).execute()
                if(request.isSuccessful){
                    response = request.body() ?: false
                }
            } catch (ex: Exception){
                response = false
            }
            response
        }
    }

    override suspend fun deleteTask(id: Long): Boolean {
        return withContext(Dispatchers.IO){
            var response = false
            try{
                val request = api.deleteTask(id).execute()
                if(request.isSuccessful){
                    response = request.body() ?: false
                }
            } catch (ex: Exception){
                response = false
            }
            response
        }
    }
}