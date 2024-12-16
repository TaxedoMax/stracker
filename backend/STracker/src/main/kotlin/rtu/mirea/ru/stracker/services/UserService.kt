package rtu.mirea.ru.stracker.services

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import rtu.mirea.ru.stracker.DTO.user.CreateUserRequest
import rtu.mirea.ru.stracker.DTO.user.CreateUserResponse
import rtu.mirea.ru.stracker.DTO.user.LoginUserRequest
import rtu.mirea.ru.stracker.entity.User
import rtu.mirea.ru.stracker.repository.UserRepository

@Service("UserService")
class UserService(
    private val userRepository: UserRepository,
    private val utils: Utils,
) {
    fun createUser(userDto: CreateUserRequest): CreateUserResponse {
        if (utils.isUserExistByLogin(userDto.login)){
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

    fun getIdByLogin(login: String): Long{
        return userRepository.findByLogin(login)?.id ?: throw EntityNotFoundException("Пользователь с таким логином не найден")
    }

    fun getLoginByUserId(id: Long): String {
        val user = userRepository.findById(id)
        return if (user.isPresent) user.get().login else throw EntityNotFoundException("Пользователь с таким id не найден")
    }
}