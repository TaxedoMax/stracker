package com.sbook.stracker.api

import com.sbook.stracker.dto.user.AuthRequest
import com.sbook.stracker.dto.user.UserDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApiService {
    @GET("/user/login/{login}/check")
    fun checkLogin(
        @Path("login")
        login: String
    ): Call<Boolean>

    @GET("/user/login/{login}")
    fun login(
        @Path("login")
        login: String,
    ): Call<Long>

    @POST("/user")
    fun register(
        @Body
        body: AuthRequest
    ): Call<UserDTO>

    @GET("/team/{teamId}/users")
    fun getUsersByTeamId(
        @Path("teamId")
        teamId: Long,
    ): Call<List<UserDTO>>
}