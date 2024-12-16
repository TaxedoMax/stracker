package rtu.mirea.ru.stracker.controllers

import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import rtu.mirea.ru.stracker.DTO.user.GetTeamsResponse
import rtu.mirea.ru.stracker.DTO.user.CreateUserRequest
import rtu.mirea.ru.stracker.DTO.user.CreateUserResponse
import rtu.mirea.ru.stracker.DTO.user.LoginUserRequest
import rtu.mirea.ru.stracker.entity.Task
import rtu.mirea.ru.stracker.entity.User
import rtu.mirea.ru.stracker.services.TeamService
import rtu.mirea.ru.stracker.services.UserService
import rtu.mirea.ru.stracker.services.Utils

@RestController
@RequestMapping("/v1/user")
class UserController(
    val userService: UserService,
    val teamService: TeamService,
    private val utils: Utils,
) {
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    fun login(
        @RequestBody loginUserRequest: LoginUserRequest
    ): Long {
        try {
            return userService.login(loginUserRequest)
        } catch (e: EntityNotFoundException){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,e.message)
        } catch (e: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,e.message)
        }
    }

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.CREATED)
    fun authenticate(
        @RequestBody loginUserRequest: LoginUserRequest
    ): Boolean {
        try {
            return userService.auth(loginUserRequest)
        } catch (e: EntityNotFoundException){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,e.message)
        } catch (e: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,e.message)
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addUser(
        @RequestBody createUserRequest: CreateUserRequest
    ): CreateUserResponse {
        try {
            return userService.createUser(createUserRequest)
        } catch (e: EntityNotFoundException){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,e.message)
        } catch (e: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,e.message)
        }
    }

    @GetMapping("/{login}/check")
    @ResponseStatus(HttpStatus.CREATED)
    fun checkUser(
        @PathVariable login: String
    ): Boolean {
        try {
            return utils.isUserExistByLogin(login)
        } catch (e: EntityNotFoundException){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,e.message)
        } catch (e: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,e.message)
        }
    }

    @GetMapping("/{id}/teams")
    @ResponseStatus(HttpStatus.OK)
    fun getTeams(
        @PathVariable id: Long
    ): GetTeamsResponse {
        try {
            return teamService.getTeams(id)
        } catch (e: EntityNotFoundException){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,e.message)
        } catch (e: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,e.message)
        }
    }

    @GetMapping("/{id}/tasks")
    @ResponseStatus(HttpStatus.OK)
    fun getTasks(
        @PathVariable id: Long
    ): List<Task> {
        try {
            return teamService.getTasks(id)
        } catch (e: EntityNotFoundException){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,e.message)
        } catch (e: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,e.message)
        }
    }

    @GetMapping("/{login}/login")
    @ResponseStatus(HttpStatus.OK)
    fun getIdByLogin(
        @PathVariable login: String,
    ): User {
        try {
            return userService.getIdByLogin(login)
        } catch (e: EntityNotFoundException){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,e.message)
        } catch (e: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,e.message)
        }
    }

    @GetMapping("/{id}/id")
    @ResponseStatus(HttpStatus.OK)
    fun getLoginByUserId(
        @PathVariable id: Long,
    ): User {
        try {
            return userService.getLoginByUserId(id)
        } catch (e: EntityNotFoundException){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,e.message)
        } catch (e: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,e.message)
        }
    }
}