package com.sbook.stracker.repository.impl

import com.sbook.stracker.api.RetrofitClient
import com.sbook.stracker.dto.team.TeamCreateRequest
import com.sbook.stracker.dto.team.TeamEditRequest
import com.sbook.stracker.dto.team.TeamResponseDTO
import com.sbook.stracker.entity.Team
import com.sbook.stracker.repository.TeamRepository

class TeamRepositoryImpl: TeamRepository {
    private val api = RetrofitClient.teamApiService
    override suspend fun getTeamById(id: Long): Team? {
        TODO("Not yet implemented")
    }

    override suspend fun getTeamsByUserId(id: Long): List<TeamResponseDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun createTeam(createTeamDTO: TeamCreateRequest): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun updateTeam(team: TeamEditRequest): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTeam(id: Long): Boolean {
        TODO("Not yet implemented")
    }

}