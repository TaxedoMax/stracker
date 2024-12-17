package com.sbook.stracker.api

import com.sbook.stracker.dto.team.GetTeamByIdRequest
import com.sbook.stracker.dto.team.GetTeamByIdResponse
import com.sbook.stracker.dto.team.NewTeamDTO
import com.sbook.stracker.dto.team.TeamEditDTO
import com.sbook.stracker.dto.team.TeamListResponse
import com.sbook.stracker.dto.user.AddUserRequest
import com.sbook.stracker.dto.user.RemoveUserRequest
import com.sbook.stracker.entity.Team
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface TeamApiService {
    @GET("user/{userId}/teams")
    fun getTeamsByUserId(
        @Path("userId")
        userId: Long,
    ): Call<TeamListResponse>

    @GET("team")
    fun getTeam(
        @Body
        getTeamRequest: GetTeamByIdRequest
    ): Call<GetTeamByIdResponse>

    @POST("team")
    fun createTeam(
        @Body
        createRequest: NewTeamDTO
    ): Call<Team>

    @PUT("team")
    fun updateTeam(
        @Body
        updateRequest: TeamEditDTO
    ): Call<Team>

    @POST("team/user")
    fun addUser(
        @Body
        addUserRequest: AddUserRequest
    ): Call<Boolean>

    @DELETE("team/user")
    fun removeUser(
        @Body
        removeUserRequest: RemoveUserRequest
    ): Call<Boolean>

    @DELETE("team/{teamId}")
    fun deleteTeam(
        @Path("teamId")
        teamId: Long,
    ): Call<Boolean>
}