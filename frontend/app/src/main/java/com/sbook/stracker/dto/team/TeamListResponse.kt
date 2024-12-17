package com.sbook.stracker.dto.team

data class TeamListResponse(
    val teams: List<TeamItem>
)

data class TeamItem(
    val id: Long,
    val name: String,
    val description: String,
    val photo: String,
    val isLeader: Boolean,
){
    fun toTeamResponse(): GetTeamByIdResponse{
        return GetTeamByIdResponse(
            id = id,
            name = name,
            isUserLead = isLeader,
        )
    }
}