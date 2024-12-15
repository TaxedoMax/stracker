package com.sbook.stracker.repository.mock

import com.sbook.stracker.dto.TaskDTO
import com.sbook.stracker.entity.Task
import com.sbook.stracker.entity.TaskStatus
import com.sbook.stracker.entity.TaskType
import com.sbook.stracker.repository.TaskRepository
import kotlinx.coroutines.delay

class InMemoryTaskRepository : TaskRepository {
    private val tasks = mutableListOf(
        Task(id = 1L, title = "Task 1", ownerId = 1, executorId = 1, teamId = 1, description = "Description for Task 1", type = TaskType.TASK, status = TaskStatus.IN_PROGRESS),
        Task(id = 2L, title = "Task 2", ownerId = 2, executorId = 1, teamId = 1, description = "Description for Task 2", type = TaskType.BUYING, status = TaskStatus.CLOSE),
        Task(id = 3L, title = "Task 3", ownerId = 3, executorId = 3, teamId = 2, description = "Description for Task 3", type = TaskType.CREATION, status = TaskStatus.CLOSE),
        Task(id = 4L, title = "Task 4", ownerId = 3, executorId = null, teamId = 2, description = "Description for Task 4", type = TaskType.HANDMADE, status = TaskStatus.OPEN),
        Task(id = 5L, title = "Task 5", ownerId = 3, executorId = 3, teamId = 2, description = "Description for Task 5", type = TaskType.TASK, status = TaskStatus.NEED_INFORMATION)
    )
    override suspend fun getTaskById(id: Long): Task? {
        delay(500L)
        return tasks.find { it.id == id }
    }

    override suspend fun addTask(task: TaskDTO): Boolean {
        delay(500L)
        val newTask = Task(
            id = (tasks.size + 1).toLong(),
            teamId = task.teamId,
            ownerId = task.ownerId,
            executorId = task.executorId,
            title = task.title,
            description = task.description,
            type = task.type,
            status = task.status,
        )
        return tasks.add(newTask)
    }

    override suspend fun updateTask(task: Task): Boolean {
        delay(500L)
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index == -1) return false
        tasks[index] = task
        return true
    }

    override suspend fun deleteTask(id: Long): Boolean {
        delay(500L)
        return tasks.removeIf { it.id == id }
    }

    override suspend fun getTasksByUserId(userId: Long): List<Task> {
        delay(500L)
        return tasks.filter { it.ownerId == userId }
    }

    override suspend fun getTasksByTeamId(teamId: Long): List<Task> {
        delay(500L)
        return tasks.filter { it.teamId == teamId }
    }
}
