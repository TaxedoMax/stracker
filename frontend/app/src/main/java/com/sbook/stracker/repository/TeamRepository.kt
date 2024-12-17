package com.sbook.stracker.repository

import com.sbook.stracker.dto.team.NewTeamDTO
import com.sbook.stracker.dto.team.TeamEditDTO
import com.sbook.stracker.dto.team.GetTeamByIdRequest
import com.sbook.stracker.dto.team.GetTeamByIdResponse
import com.sbook.stracker.entity.Team

interface TeamRepository {
    suspend fun getTeamById(teamRequest: GetTeamByIdRequest): Team?
    suspend fun getTeamsByUserId(id: Long): List<GetTeamByIdResponse>
    suspend fun createTeam(createTeamDTO: NewTeamDTO): Boolean
    suspend fun updateTeam(team: TeamEditDTO): Boolean
    suspend fun deleteTeam(id: Long): Boolean
}