package com.sbook.stracker.repository.mock

import com.sbook.stracker.dto.team.CreateTeamDTO
import com.sbook.stracker.entity.Team
import com.sbook.stracker.entity.User
import com.sbook.stracker.repository.TeamRepository
import kotlinx.coroutines.delay

class InMemoryTeamRepository : TeamRepository {

    private val teams = mutableListOf(
        Team(
            id = "1",
            name = "Team Alpha",
            adminId = "1"
        ),
        Team(
            id = "2",
            name = "Team Beta",
            adminId = "2"
        )
    )

    private val teamUsers: MutableMap<String, MutableList<String>> = mutableMapOf(
        "1" to mutableListOf("1", "2"),
        "2" to mutableListOf("2", "3"),
    )

    fun getUsersByTeam(teamId: String): List<String> {
        return teamUsers[teamId] ?: emptyList()
    }

    override fun getTeamById(id: String): Team? = teams.find { it.id == id }
    override suspend fun getTeamsByUserId(id: String): List<Team> {
        delay(500)

        val tmpTeams: ArrayList<String> = arrayListOf()
        teamUsers.forEach {
            if(it.value.contains(id)) tmpTeams.add(it.key)
        }

        return teams.filter {tmpTeams.contains(it.id)}
    }

    override fun createTeam(createTeamDTO: CreateTeamDTO): Boolean {
        val id = (teams.size + 1).toString()
        val newTeam = Team(
            id = id,
            name = createTeamDTO.name,
            adminId = createTeamDTO.adminId,
        )

        teamUsers[id] = createTeamDTO.usersIdsList.toMutableList()
        return teams.add(newTeam)
    }

    override fun updateTeam(team: Team, userIdsList: List<String>): Boolean {
        val index = teams.indexOfFirst { it.id == team.id }
        if (index == -1) return false
        teams[index] = team

        teamUsers[team.id]?.clear()
        teamUsers[team.id]?.addAll(userIdsList)
        return true
    }

    override fun deleteTeam(id: String): Boolean {
        return teams.removeIf { it.id == id }
    }
}
