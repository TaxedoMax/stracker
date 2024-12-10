package rtu.mirea.ru.stracker.controllers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import rtu.mirea.ru.stracker.DTO.task.CreateTaskRequest
import rtu.mirea.ru.stracker.DTO.task.CreateTaskResponse
import rtu.mirea.ru.stracker.services.TaskService

@RestController
@RequestMapping("/v1/task")
class TaskController(
    val taskService: TaskService,
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addTask(
        @RequestBody request: CreateTaskRequest,
    ): CreateTaskResponse{
        return taskService.create(request)
    }
}