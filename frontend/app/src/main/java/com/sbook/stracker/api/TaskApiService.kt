package com.sbook.stracker.api

import com.sbook.stracker.dto.TaskDTO
import com.sbook.stracker.entity.Task
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TaskApiService {
    @GET("/task/{taskId}")
    fun getTaskById(
        @Path("taskId")
        taskId: Long,
    ): Call<Task>

    @GET("/task/team/{teamId}")
    fun getTasksByTeamId(
        @Path("teamId")
        teamId: Long,
    ): Call<List<Task>>

    @GET("/user/{userId}/tasks")
    fun getTasksByUserId(
        @Path("userId")
        userId: Long
    ): Call<List<Task>>

    @POST("/task")
    fun createTask(
        @Body
        task: TaskDTO
    )
}