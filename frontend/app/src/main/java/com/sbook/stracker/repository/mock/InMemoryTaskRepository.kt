package com.sbook.stracker.repository.mock

import com.sbook.stracker.entity.Task
import com.sbook.stracker.repository.TaskRepository
import kotlinx.coroutines.delay

class InMemoryTaskRepository : TaskRepository {
    private val tasks = mutableListOf(
        Task(id = "1", title = "Task 1", ownerId = "1", teamId = "1", description = "Description for Task 1", status = "Pending"),
        Task(id = "2", title = "Task 2", ownerId = "2", teamId = "1", description = "Description for Task 2", status = "In Progress"),
        Task(id = "3", title = "Task 3", ownerId = "3", teamId = "2", description = "Description for Task 3", status = "Completed"),
        Task(id = "4", title = "Task 4", ownerId = "3", teamId = "2", description = "Description for Task 4", status = "Pending"),
        Task(id = "5", title = "Task 5", ownerId = "3", teamId = "2", description = "Description for Task 5", status = "In Progress")
    )
    override suspend fun getTaskById(id: String): Task? {
        delay(500L)
        return tasks.find { it.id == id }
    }

    override suspend fun addTask(task: Task): Boolean {
        delay(500L)
        return tasks.add(task)
    }

    override suspend fun updateTask(task: Task): Boolean {
        delay(500L)
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index == -1) return false
        tasks[index] = task
        return true
    }

    override suspend fun deleteTask(id: String): Boolean {
        delay(500L)
        return tasks.removeIf { it.id == id }
    }

    override suspend fun getTasksByUserId(userId: String): List<Task> {
        delay(500L)
        return tasks.filter { it.ownerId == userId }
    }

    override suspend fun getTasksByTeamId(teamId: String): List<Task> {
        delay(500L)
        return tasks.filter { it.teamId == teamId }
    }
}
