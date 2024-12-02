package com.sbook.stracker.entity

data class Team(
    val id: String,
    val name: String,
    val usersIds: List<String>,
    val adminIds: List<String>,
    val tasksIds: List<String>
)
