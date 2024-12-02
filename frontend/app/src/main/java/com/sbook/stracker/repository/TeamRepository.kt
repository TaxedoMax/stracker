package com.sbook.stracker.repository

import com.sbook.stracker.entity.Team

interface TeamRepository {
    fun getAllTeams(): List<Team>
    fun getTeamById(id: String): Team?
    fun getTeamsByUserId(id: String): List<Team>
    fun addTeam(team: Team): Boolean
    fun updateTeam(team: Team): Boolean
    fun deleteTeam(id: String): Boolean
}