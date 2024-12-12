package com.sbook.stracker.repository

import com.sbook.stracker.dto.team.TeamCreateRequest
import com.sbook.stracker.dto.team.EditTeamDTO
import com.sbook.stracker.dto.team.TeamResponseDTO
import com.sbook.stracker.entity.Team

interface TeamRepository {
    fun getTeamById(id: String): Team?
    suspend fun getTeamsByUserId(id: String): List<TeamResponseDTO>
    fun createTeam(createTeamDTO: TeamCreateRequest): Boolean
    fun updateTeam(team: EditTeamDTO): Boolean
    fun deleteTeam(id: String): Boolean
}