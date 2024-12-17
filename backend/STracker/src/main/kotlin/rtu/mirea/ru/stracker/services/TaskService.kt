package rtu.mirea.ru.stracker.services

import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import rtu.mirea.ru.stracker.DTO.task.CreateTaskRequest
import rtu.mirea.ru.stracker.DTO.task.CreateTaskResponse
import rtu.mirea.ru.stracker.DTO.task.EditTaskRequest
import rtu.mirea.ru.stracker.entity.Task
import rtu.mirea.ru.stracker.entity.TaskStatus
import rtu.mirea.ru.stracker.entity.TaskType
import rtu.mirea.ru.stracker.repository.TaskRepository
import rtu.mirea.ru.stracker.repository.UserRepository

@Service
class TaskService(
    private val taskRepository: TaskRepository,
    private val userRepository: UserRepository,
    private val utils: Utils,
){
    fun create(request: CreateTaskRequest): CreateTaskResponse{
        validateCreateRequest(request)
        val executorId: Long? = if (request.executorLogin != null) {
            userRepository.findByLogin(request.executorLogin)?.id
        } else {
            null
        }

        val task = Task(
            name = request.name,
            status = TaskStatus.OPEN,
            type = TaskType.valueOf(request.type.toString()),
            description = request.description,
            authorId = request.authorId,
            executorId = executorId,
            teamId = request.teamId,
        )
        val result = taskRepository.save(task)

        return CreateTaskResponse(
            id = result.id,
            name = result.name,
            status = result.status,
            type = result.type,
            description = result.description,
            authorId = result.authorId,
            executorLogin = request.executorLogin,
            teamId = result.teamId
        )
    }

    private fun validateCreateRequest(request: CreateTaskRequest){
        val errors = mutableListOf<String>()

        if (!utils.isUserExistById(request.authorId)){
            errors.add("Автора с таким Id не найдено:(")
        }
        if (request.executorLogin != null && !utils.isUserExistByLogin(request.executorLogin)){
            errors.add("исполнитель с таким логином не найден")
        }
        if (request.name.isEmpty()) {
            errors.add("Название таски обязательно для заполнения")
        }
        try {
            TaskType.valueOf(request.type.toString())
        } catch (e: IllegalArgumentException) {
            errors.add("${request.type} не существующий тип таски")
        }

        if (errors.isNotEmpty()) {
            throw IllegalArgumentException(errors.joinToString(separator = "; "))
        }
    }

    @Transactional
    fun editTask(request: EditTaskRequest): Task{
        val task = taskRepository.findById(request.taskId).orElse(null) ?: throw EntityNotFoundException("Таска не найдена")
        if (!utils.isUserInTeam(task.teamId, request.editorId)){
            throw IllegalArgumentException("Пользователь, пытающийся изменить таску, не состоит в команде")
        }
        task.name = request.name
        task.type = request.type
        task.description = request.description
        if (request.executorLogin != null){
            val userId = utils.takeIdByLogin(request.executorLogin)
            if (!utils.isUserExistById(userId)) {
                throw EntityNotFoundException("Пользователь не найдена")
            }
            if (!utils.isUserInTeam(task.teamId, userId)){
                throw IllegalArgumentException("Пользователь не в команде")
            } else {
                task.executorId = userId
            }
        } else {
            task.executorId = null
        }
        taskRepository.save(task)

        return task
    }

    fun getTask(id: Long): Task{
        return taskRepository.findById(id).get() ?: throw EntityNotFoundException("Таски с таким id не существует")
    }

    fun getTasksByTeam(id: Long): List<Task>{
        return taskRepository.findAllByTeamId(id)
    }
}