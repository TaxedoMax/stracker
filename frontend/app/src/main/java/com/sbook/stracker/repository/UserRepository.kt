package com.sbook.stracker.repository

import com.sbook.stracker.dto.user.AuthDTO
import com.sbook.stracker.dto.user.UserDTO
import com.sbook.stracker.entity.User

interface UserRepository {
    fun getUserById(id: String): UserDTO?
    fun getUserByLogin(login: String): UserDTO?
    fun getUsersByTeam(teamId: String): List<UserDTO>
    fun registerUser(authDTO: AuthDTO): String
    fun updateUser(user: User): Boolean
    fun login(authDTO: AuthDTO): String
}

