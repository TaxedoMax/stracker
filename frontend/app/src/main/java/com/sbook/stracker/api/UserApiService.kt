package com.sbook.stracker.api

import com.sbook.stracker.dto.user.AuthRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface UserApiService {
    @GET("/user/login")
    fun login(
        @Body
        body: AuthRequest
    ): Call<Long>

    @GET("/user/register")
    fun register(
        @Body
        body: AuthRequest
    ): Call<Long>
}