package com.sbook.stracker.repository

import com.sbook.stracker.dto.team.TeamCreateRequest
import com.sbook.stracker.dto.team.TeamEditRequest
import com.sbook.stracker.dto.team.TeamResponseDTO
import com.sbook.stracker.entity.Team

interface TeamRepository {
    suspend fun getTeamById(id: String): Team?
    suspend fun getTeamsByUserId(id: String): List<TeamResponseDTO>
    suspend fun createTeam(createTeamDTO: TeamCreateRequest): Boolean
    suspend fun updateTeam(team: TeamEditRequest): Boolean
    suspend fun deleteTeam(id: String): Boolean
}