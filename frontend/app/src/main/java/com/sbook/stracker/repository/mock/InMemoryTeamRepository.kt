package com.sbook.stracker.repository.mock

import com.sbook.stracker.dto.team.TeamCreateRequest
import com.sbook.stracker.dto.team.TeamEditRequest
import com.sbook.stracker.dto.team.TeamResponseDTO
import com.sbook.stracker.entity.Team
import com.sbook.stracker.repository.TeamRepository
import kotlinx.coroutines.delay

class InMemoryTeamRepository : TeamRepository {

    private val teams = mutableListOf(
        Team(
            id = 1,
            name = "Team Alpha",
            adminId = 1
        ),
        Team(
            id = 2,
            name = "Team Beta",
            adminId = 2
        )
    )

    private val teamUsers: MutableMap<Long, MutableList<Long>> = mutableMapOf(
        1L to mutableListOf(1, 2),
        2L to mutableListOf(2, 3),
    )

    fun getUsersByTeam(teamId: Long): List<Long> {
        return teamUsers[teamId] ?: emptyList()
    }

    override suspend fun getTeamById(id: Long): Team? = teams.find { it.id == id }
    override suspend fun getTeamsByUserId(id: Long): List<TeamResponseDTO> {
        delay(500)

        val tmpTeams: ArrayList<Long> = arrayListOf()
        teamUsers.forEach {
            if(it.value.contains(id)) tmpTeams.add(it.key)
        }

        return teams.filter {tmpTeams.contains(it.id)}
            .map{team ->
                TeamResponseDTO(
                    id = team.id,
                    name = team.name,
                    isOwner = team.adminId == id
                )
            }
    }

    override suspend fun createTeam(createTeamDTO: TeamCreateRequest): Boolean {
        val id = (teams.size + 1).toLong()
        val newTeam = Team(
            id = id,
            name = createTeamDTO.name,
            adminId = createTeamDTO.adminId,
        )

        teamUsers[id] = createTeamDTO.usersIdsList.toMutableList()
        return teams.add(newTeam)
    }

    override suspend fun updateTeam(team: TeamEditRequest): Boolean {
        val index = teams.indexOfFirst { it.id == team.id }
        if (index == -1) return false
        teams[index] = teams[index].copy(name = team.name)
        teamUsers[team.id]?.clear()
        teamUsers[team.id]?.addAll(team.usersIdsList)
        return true
    }

    override suspend fun deleteTeam(id: Long): Boolean {
        return teams.removeIf { it.id == id }
    }
}
