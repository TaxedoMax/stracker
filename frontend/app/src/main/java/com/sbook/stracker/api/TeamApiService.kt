package com.sbook.stracker.api

import com.sbook.stracker.dto.team.TeamCreateRequest
import com.sbook.stracker.dto.team.TeamResponse
import com.sbook.stracker.dto.user.AddUserRequest
import com.sbook.stracker.entity.Team
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TeamApiService {
    @GET("user/{userId}/teams")
    fun getTeamsByUserId(
        @Path("userId")
        userId: Long,
    ): Call<List<TeamResponse>>

    @GET("/team")
    fun getTeam(
        @Query("userId")
        userId: Long,
        @Query("teamId")
        teamId: Long,
    ): Call<Team>

    @POST("/team")
    fun createTeam(
        @Body
        createRequest: TeamCreateRequest
    ): Call<Team>

    @POST("/team/user")
    fun addUser(
        @Body
        addUserRequest: AddUserRequest
    ): Call<Boolean>

    @DELETE("/team/user")
    fun removeUser(
        @Body
        removeUserRequest: AddUserRequest
    ): Call<Boolean>
}