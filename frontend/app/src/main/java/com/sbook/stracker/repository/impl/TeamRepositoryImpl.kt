package com.sbook.stracker.repository.impl

import com.sbook.stracker.api.RetrofitClient
import com.sbook.stracker.dto.team.NewTeamDTO
import com.sbook.stracker.dto.team.TeamEditDTO
import com.sbook.stracker.dto.team.GetTeamByIdRequest
import com.sbook.stracker.dto.team.GetTeamByIdResponse
import com.sbook.stracker.entity.Team
import com.sbook.stracker.repository.TeamRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TeamRepositoryImpl: TeamRepository {
    private val api = RetrofitClient.teamApiService
    override suspend fun getTeamById(teamRequest: GetTeamByIdRequest): Team? {
        return withContext(Dispatchers.IO){
            var response: Team? = null

            try{
                val request = api.getTeam(teamRequest.userId, teamRequest.teamId).execute()
                if(request.isSuccessful){
                    response = request.body()
                }
            } catch (ex: Exception){
                response = null
            }

            response
        }
    }

    override suspend fun getTeamsByUserId(id: Long): List<GetTeamByIdResponse> {
        return withContext(Dispatchers.IO){
            var response = emptyList<GetTeamByIdResponse>()
            try{
                val request = api.getTeamsByUserId(id).execute()
                if(request.isSuccessful){
                    response = request.body() ?: emptyList()
                }
            } catch (ex: Exception){
                response = emptyList()
            }

            response
        }
    }

    override suspend fun createTeam(createTeamDTO: NewTeamDTO): Boolean {
        return withContext(Dispatchers.IO){
            var response = false

            try{
                val request = api.createTeam(createTeamDTO).execute()
                if(request.isSuccessful){
                    response = request.body() != null
                }
            } catch (ex: Exception){
                response = false
            }

            response
        }
    }

    override suspend fun updateTeam(team: TeamEditDTO): Boolean {
        return withContext(Dispatchers.IO){
            var response = false

            try{
                val request = api.updateTeam(team).execute()
                if(request.isSuccessful){
                    response = request.body() != null
                }
            } catch (ex: Exception){
                response = false
            }

            response
        }
    }

    override suspend fun deleteTeam(id: Long): Boolean {
        return withContext(Dispatchers.IO){
            var response = false

            try{
                val request = api.deleteTeam(id).execute()
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