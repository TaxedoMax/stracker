package rtu.mirea.ru.stracker.controllers

import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
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
        try {
            return taskService.create(request)
        } catch (e: EntityNotFoundException){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,e.message)
        } catch (e: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,e.message)
        }
    }
}