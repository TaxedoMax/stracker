package com.sbook.stracker.repository.mock

import com.sbook.stracker.entity.Team
import com.sbook.stracker.repository.TeamRepository

class InMemoryTeamRepository : TeamRepository {
    private val teams = mutableListOf(
        Team(
            id = "1",
            name = "Team Alpha",
            usersIds = listOf("1", "2"),
            adminIds = listOf("1"),
            tasksIds = listOf("1", "2")
        ),
        Team(
            id = "2",
            name = "Team Beta",
            usersIds = listOf("2", "3"),
            adminIds = listOf("2"),
            tasksIds = listOf("3", "4", "5")
        )
    )

    override fun getAllTeams(): List<Team> = teams

    override fun getTeamById(id: String): Team? = teams.find { it.id == id }
    override fun getTeamsByUserId(id: String): List<Team> {
        return teams.filter { it.usersIds.contains(id) }
    }

    override fun addTeam(team: Team): Boolean {
        return teams.add(team)
    }

    override fun updateTeam(team: Team): Boolean {
        val index = teams.indexOfFirst { it.id == team.id }
        if (index == -1) return false
        teams[index] = team
        return true
    }

    override fun deleteTeam(id: String): Boolean {
        return teams.removeIf { it.id == id }
    }
}
