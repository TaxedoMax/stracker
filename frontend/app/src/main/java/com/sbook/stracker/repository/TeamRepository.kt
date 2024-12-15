package com.sbook.stracker.repository

import com.sbook.stracker.dto.team.TeamCreateRequest
import com.sbook.stracker.dto.team.TeamEditRequest
import com.sbook.stracker.dto.team.TeamGetRequest
import com.sbook.stracker.dto.team.TeamResponse
import com.sbook.stracker.entity.Team

interface TeamRepository {
    suspend fun getTeamById(teamRequest: TeamGetRequest): Team?
    suspend fun getTeamsByUserId(id: Long): List<TeamResponse>
    suspend fun createTeam(createTeamDTO: TeamCreateRequest): Boolean
    suspend fun updateTeam(team: TeamEditRequest): Boolean
    suspend fun deleteTeam(id: Long): Boolean
}