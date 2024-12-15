package com.sbook.stracker.repository

import com.sbook.stracker.dto.user.AuthRequest
import com.sbook.stracker.dto.user.UserDTO
import com.sbook.stracker.entity.User

interface UserRepository {
    suspend fun getUserById(id: Long): UserDTO?
    suspend fun getUserByLogin(login: String): UserDTO?
    suspend fun getUsersByTeam(teamId: Long): List<UserDTO>
    suspend fun register(authDTO: AuthRequest): Long
    suspend fun updateUser(user: User): Boolean
    suspend fun login(authDTO: AuthRequest): Long
}

