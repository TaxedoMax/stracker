package com.sbook.stracker.repository

import com.sbook.stracker.entity.Task

interface TaskRepository {
    fun getAllTasks(): List<Task>
    fun getTaskById(id: String): Task?
    fun addTask(task: Task): Boolean
    fun updateTask(task: Task): Boolean
    fun deleteTask(id: String): Boolean
    fun getTasksByUserId(userId: String): List<Task>
}
