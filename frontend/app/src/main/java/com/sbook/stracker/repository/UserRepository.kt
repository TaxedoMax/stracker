package com.sbook.stracker.repository

import com.sbook.stracker.dto.AuthDTO
import com.sbook.stracker.entity.User

interface UserRepository {
    fun getUserById(id: String): User?
    fun getUserByLogin(login: String): User?
    fun getUsersByTeam(teamId: String): List<User>
    fun registerUser(authDTO: AuthDTO): String
    fun updateUser(user: User): Boolean
    fun login(authDTO: AuthDTO): String
}

