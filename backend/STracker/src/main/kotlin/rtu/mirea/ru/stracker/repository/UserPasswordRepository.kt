package rtu.mirea.ru.stracker.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import rtu.mirea.ru.stracker.entity.UserPassword

@Repository
interface UserPasswordRepository: JpaRepository<UserPassword, String>{
    fun existsByLoginAndPassword(login: String, password: String): Boolean
}