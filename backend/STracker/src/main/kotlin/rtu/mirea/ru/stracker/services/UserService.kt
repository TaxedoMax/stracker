package rtu.mirea.ru.stracker.services

import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import rtu.mirea.ru.stracker.DTO.user.CreateUserRequest
import rtu.mirea.ru.stracker.DTO.user.CreateUserResponse
import rtu.mirea.ru.stracker.DTO.user.LoginUserRequest
import rtu.mirea.ru.stracker.entity.User
import rtu.mirea.ru.stracker.entity.UserPassword
import rtu.mirea.ru.stracker.repository.UserPasswordRepository
import rtu.mirea.ru.stracker.repository.UserRepository

@Service("UserService")
class UserService(
    private val userRepository: UserRepository,
    private val utils: Utils,
    private val userPasswordRepository: UserPasswordRepository,
) {
    fun auth(
        loginUserRequest: LoginUserRequest,
    ): Boolean{
        return userPasswordRepository.existsByLoginAndPassword(loginUserRequest.login, loginUserRequest.password)
    }

    @Transactional
    fun login(
        loginUserRequest: LoginUserRequest,
    ): Long{
        val userId = createUser(
            CreateUserRequest(
                login = loginUserRequest.login,
                photo = null,
            ),
        ).id

        val userPassword = UserPassword(
            login = loginUserRequest.login,
            password = loginUserRequest.password,
        )

        userPasswordRepository.save(userPassword)

        return userId
    }

    @Transactional
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

    fun getIdByLogin(login: String): User{
        return userRepository.findByLogin(login) ?: throw EntityNotFoundException("Пользователь с таким логином не найден")
    }

    fun getLoginByUserId(id: Long): User {
        val user = userRepository.findById(id)
        return if (user.isPresent) user.get() else throw EntityNotFoundException("Пользователь с таким id не найден")
    }
}