package rtu.mirea.ru.stracker.controllers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import rtu.mirea.ru.stracker.DTO.CreateUserDto
import rtu.mirea.ru.stracker.DTO.UserDto
import rtu.mirea.ru.stracker.services.UserService

@RestController
@RequestMapping("/v1/user")
class UserController(
    val userService: UserService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addUser(
        @RequestBody createUserDto: CreateUserDto
    ): UserDto{
        return userService.createUser(createUserDto)
    }
}