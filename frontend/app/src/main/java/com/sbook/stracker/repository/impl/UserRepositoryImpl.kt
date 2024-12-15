package com.sbook.stracker.repository.impl

import com.sbook.stracker.api.RetrofitClient
import com.sbook.stracker.dto.user.AuthRequest
import com.sbook.stracker.dto.user.UserDTO
import com.sbook.stracker.entity.User
import com.sbook.stracker.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl : UserRepository {
    private val api = RetrofitClient.userApiService

    override suspend fun register(authDTO: AuthRequest): Long {
        return withContext(Dispatchers.IO){
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

            response
        }
    }

    override suspend fun login(authDTO: AuthRequest): Long {
        return withContext(Dispatchers.IO){
            var response = -1L

            try{
                val request = api.login(authDTO.login).execute()
                if(request.isSuccessful){
                    response = request.body() ?: 1L
                } else{
                    response = 1L
                }
            } catch (ex: Exception){
                response = 1L
            }

            response
        }
    }

    override suspend fun getUsersByTeam(teamId: Long): List<UserDTO> {
        return withContext(Dispatchers.IO){
            var response = emptyList<UserDTO>()

            try{
                val request = api.getUsersByTeamId(teamId).execute()
                if(request.isSuccessful){
                    response = request.body() ?: emptyList()
                }
            } catch (ex: Exception){
                response = emptyList()
            }

            response
        }
    }

    override suspend fun getUserById(id: Long): UserDTO? {
        return withContext(Dispatchers.IO){
            var response: UserDTO? = null
            try{
                val request = api.getUserById(id).execute()
                if(request.isSuccessful){
                    response = request.body()
                }
            } catch (ex: Exception){
                response = null
            }
            response
        }
    }

    override suspend fun getUserByLogin(login: String): UserDTO? {
        return withContext(Dispatchers.IO){
            var response: UserDTO? = null
            try{
                val request = api.getUserByLogin(login).execute()
                if(request.isSuccessful){
                    response = request.body()
                }
            } catch (ex: Exception){
                response = null
            }
            response
        }
    }

    override suspend fun updateUser(user: User): Boolean {
        return withContext(Dispatchers.IO){
            var response = false
            try{
                val request = api.updateUser(user).execute()
                if(request.isSuccessful){
                    response = request.body() ?: false
                }
            } catch (ex: Exception){
                response = false
            }
            response
        }
    }
}