package rtu.mirea.ru.stracker.services

import org.springframework.stereotype.Service
import rtu.mirea.ru.stracker.DTO.CreateUserDto
import rtu.mirea.ru.stracker.DTO.UserDto
import rtu.mirea.ru.stracker.entity.User
import rtu.mirea.ru.stracker.repository.UserRepository

@Service("UserService")
class UserService(
    private val userRepository: UserRepository,
) {
    fun createUser(userDto: CreateUserDto): UserDto {
        val user = User(
            login = userDto.login,
            photo = userDto.photo,
        )
        val result = userRepository.save(user)
        return UserDto(
            result.id,
            result.login,
            result.photo
        )
    }
}