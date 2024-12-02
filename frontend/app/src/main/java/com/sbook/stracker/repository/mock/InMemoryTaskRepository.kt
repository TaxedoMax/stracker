package com.sbook.stracker.repository.mock

import com.sbook.stracker.entity.Task
import com.sbook.stracker.repository.TaskRepository

class InMemoryTaskRepository : TaskRepository {
    private val tasks = mutableListOf(
        Task(id = "1", title = "Task 1", ownerId = "1", description = "Description for Task 1", status = "Pending"),
        Task(id = "2", title = "Task 2", ownerId = "2", description = "Description for Task 2", status = "In Progress"),
        Task(id = "3", title = "Task 3", ownerId = "3", description = "Description for Task 3", status = "Completed"),
        Task(id = "4", title = "Task 4", ownerId = "3", description = "Description for Task 4", status = "Pending"),
        Task(id = "5", title = "Task 5", ownerId = "3", description = "Description for Task 5", status = "In Progress")
    )

    override fun getAllTasks(): List<Task> = tasks

    override fun getTaskById(id: String): Task? = tasks.find { it.id == id }

    override fun addTask(task: Task): Boolean {
        return tasks.add(task)
    }

    override fun updateTask(task: Task): Boolean {
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index == -1) return false
        tasks[index] = task
        return true
    }

    override fun deleteTask(id: String): Boolean {
        return tasks.removeIf { it.id == id }
    }

    override fun getTasksByUserId(userId: String): List<Task> {
        return  tasks.filter { it.ownerId == userId }
    }
}
