package com.sbook.stracker.api

import com.sbook.stracker.dto.user.AuthRequest
import com.sbook.stracker.dto.user.UserDTO
import com.sbook.stracker.dto.user.UserListResponse
import com.sbook.stracker.entity.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserApiService {
    @GET("user/login/{login}/check")
    fun checkLogin(
        @Path("login")
        login: String
    ): Call<Boolean>

    @POST("user/login")
    // анекдот, спросить у Ильи
    fun register(
        @Body
        auth: AuthRequest,
    ): Call<Long>

    @GET("user/{login}/login")
    fun getUserByLogin(
        @Path("login")
        login: String,
    ): Call<UserDTO>

    @GET("user/{userId}/id")
    fun getUserById(
        @Path("userId")
        userId: Long,
    ): Call<UserDTO>

    @POST("user/auth")
    fun tryLogin(
        @Body
        body: AuthRequest
    ): Call<Boolean>

    @GET("team/{teamId}/users")
    fun getUsersByTeamId(
        @Path("teamId")
        teamId: Long,
    ): Call<UserListResponse>

    @PUT("user")
    fun updateUser(
        @Body
        body: User,
    ): Call<Boolean>
}