package com.sbook.stracker.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://localhost:8080"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val userApiService: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }

    val teamApiService: TeamApiService by lazy {
        retrofit.create(TeamApiService::class.java)
    }

    val taskApiService: TaskApiService by lazy{
        retrofit.create(TaskApiService::class.java)
    }
}