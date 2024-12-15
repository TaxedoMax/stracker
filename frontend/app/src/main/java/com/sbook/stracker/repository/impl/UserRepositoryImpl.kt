package com.sbook.stracker.repository.impl

import com.sbook.stracker.api.RetrofitClient
import com.sbook.stracker.dto.user.AuthRequest
import com.sbook.stracker.dto.user.UserDTO
import com.sbook.stracker.entity.User
import com.sbook.stracker.repository.UserRepository

class UserRepositoryImpl : UserRepository {
    private val api = RetrofitClient.userApiService

    override suspend fun register(authDTO: AuthRequest): Long {
        var response = -1L

        try{
            val request = api.register(authDTO).execute()
            if(request.isSuccessful){
                val dto = request.body()
                response = dto?.id ?: -1L
            }
        } catch (ex: Exception){
            response = -1L
        }

        return response
    }

    override suspend fun login(authDTO: AuthRequest): Long {
        var response = -1L

        try{
            val request = api.login(authDTO.login).execute()
            if(request.isSuccessful){
                response = request.body() ?: -1L
            }
        } catch (ex: Exception){
            response = -1L
        }

        return response
    }

    override suspend fun getUsersByTeam(teamId: Long): List<UserDTO> {
        var response = emptyList<UserDTO>()

        try{
            val request = api.getUsersByTeamId(teamId).execute()
            if(request.isSuccessful){
                response = request.body() ?: emptyList()
            }
        } catch (ex: Exception){
            response = emptyList()
        }

        return response
    }

    override suspend fun getUserById(id: Long): UserDTO? {
        TODO("Not yet implemented")
    }

    override suspend fun getUserByLogin(login: String): UserDTO? {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(user: User): Boolean {
        TODO("Not yet implemented")
    }
}