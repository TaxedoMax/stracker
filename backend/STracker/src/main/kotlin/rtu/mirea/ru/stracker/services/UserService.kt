package rtu.mirea.ru.stracker.services

import org.springframework.stereotype.Service
import rtu.mirea.ru.stracker.DTO.user.CreateUserRequest
import rtu.mirea.ru.stracker.DTO.user.CreateUserResponse
import rtu.mirea.ru.stracker.entity.User
import rtu.mirea.ru.stracker.repository.UserRepository

@Service("UserService")
class UserService(
    private val userRepository: UserRepository,
) {
    fun createUser(userDto: CreateUserRequest): CreateUserResponse {
        if (isUserExist(userDto.login)){
            throw IllegalArgumentException("Пользователь с таким именем уже есть")
        }
        val user = User(
            login = userDto.login,
            photo = userDto.photo,
        )
        val result = userRepository.save(user)
        return CreateUserResponse(
            result.id,
            result.login,
            result.photo
        )
    }

    fun isUserExist(username: String): Boolean {
        val user = userRepository.findByLogin(username)
        return user != null
    }
}