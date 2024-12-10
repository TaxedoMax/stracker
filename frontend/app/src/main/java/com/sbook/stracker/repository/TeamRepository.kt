package com.sbook.stracker.repository

import com.sbook.stracker.dto.team.CreateTeamDTO
import com.sbook.stracker.dto.team.EditTeamDTO
import com.sbook.stracker.dto.team.TeamForUserDTO
import com.sbook.stracker.entity.Team

interface TeamRepository {
    fun getTeamById(id: String): Team?
    suspend fun getTeamsByUserId(id: String): List<TeamForUserDTO>
    fun createTeam(createTeamDTO: CreateTeamDTO): Boolean
    fun updateTeam(team: EditTeamDTO): Boolean
    fun deleteTeam(id: String): Boolean
}