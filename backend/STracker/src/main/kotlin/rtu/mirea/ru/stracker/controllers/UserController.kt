package rtu.mirea.ru.stracker.controllers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import rtu.mirea.ru.stracker.DTO.user.CreateUserRequest
import rtu.mirea.ru.stracker.DTO.user.CreateUserResponse
import rtu.mirea.ru.stracker.services.UserService

@RestController
@RequestMapping("/v1/user")
class UserController(
    val userService: UserService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addUser(
        @RequestBody createUserRequest: CreateUserRequest
    ): CreateUserResponse {
        try {
            return userService.createUser(createUserRequest)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @GetMapping("/check/{login}")
    @ResponseStatus(HttpStatus.CREATED)
    fun checkUser(
        @PathVariable login: String
    ): Boolean {
        try {
            return userService.isUserExist(login)
        } catch (e: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,e.message)
        }
    }
}