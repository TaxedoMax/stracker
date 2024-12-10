package com.sbook.stracker.repository

import com.sbook.stracker.dto.team.CreateTeamDTO
import com.sbook.stracker.entity.Team

interface TeamRepository {
    fun getTeamById(id: String): Team?
    suspend fun getTeamsByUserId(id: String): List<Team>
    fun createTeam(createTeamDTO: CreateTeamDTO): Boolean
    fun updateTeam(team: Team, userIdsList: List<String>): Boolean
    fun deleteTeam(id: String): Boolean
}