package com.sbook.stracker.repository.impl

import com.sbook.stracker.api.RetrofitClient
import com.sbook.stracker.dto.user.AuthRequest
import com.sbook.stracker.dto.user.UserDTO
import com.sbook.stracker.entity.User
import com.sbook.stracker.repository.UserRepository

class UserRepositoryImpl : UserRepository {
    private val api = RetrofitClient.userApiService
    override suspend fun getUserById(id: Long): UserDTO? {
        TODO("Not yet implemented")
    }

    override suspend fun getUserByLogin(login: String): UserDTO? {
        TODO("Not yet implemented")
    }

    override suspend fun getUsersByTeam(teamId: Long): List<UserDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(user: User): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun register(authDTO: AuthRequest): Long {
        TODO("Not yet implemented")
    }

    override suspend fun login(authDTO: AuthRequest): Long {
        val request = api.login(authDTO).execute()
        var result = -1L
        try{
            if(request.isSuccessful){
                result = request.body() ?: -1L
            }
        } catch (ex: Exception){
            result = -1L
        }

        return result
    }
}