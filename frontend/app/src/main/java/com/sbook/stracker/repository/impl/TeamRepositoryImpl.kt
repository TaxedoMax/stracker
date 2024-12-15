package com.sbook.stracker.repository.impl

import com.sbook.stracker.api.RetrofitClient
import com.sbook.stracker.dto.team.TeamCreateRequest
import com.sbook.stracker.dto.team.TeamEditRequest
import com.sbook.stracker.dto.team.TeamGetRequest
import com.sbook.stracker.dto.team.TeamResponse
import com.sbook.stracker.entity.Team
import com.sbook.stracker.repository.TeamRepository

class TeamRepositoryImpl: TeamRepository {
    private val api = RetrofitClient.teamApiService
    override suspend fun getTeamById(teamRequest: TeamGetRequest): Team? {
        var response: Team? = null

        try{
            val request = api.getTeam(teamRequest.userId, teamRequest.teamId).execute()
            if(request.isSuccessful){
                response = request.body()
            }
        } catch (ex: Exception){
            response = null
        }

        return response
    }

    override suspend fun getTeamsByUserId(id: Long): List<TeamResponse> {
        var response = emptyList<TeamResponse>()

        try{
            val request = api.getTeamsByUserId(id).execute()
            if(request.isSuccessful){
                response = request.body() ?: emptyList()
            }
        } catch (ex: Exception){
            response = emptyList()
        }

        return response
    }

    override suspend fun createTeam(createTeamDTO: TeamCreateRequest): Boolean {
        var response = false

        try{
            val request = api.createTeam(createTeamDTO).execute()
            if(request.isSuccessful){
                response = request.body() != null
                // TODO
            }
        } catch (ex: Exception){
            response = false
        }

        return response
    }

    private suspend fun addUser(){

    }

    private suspend fun removeUser(){

    }

    override suspend fun updateTeam(team: TeamEditRequest): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTeam(id: Long): Boolean {
        TODO("Not yet implemented")
    }

}