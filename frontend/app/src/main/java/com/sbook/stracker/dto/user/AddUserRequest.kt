package com.sbook.stracker.dto.user

data class AddUserRequest(
    val userId: Long,
    val userLogin: String,
    val teamId: Long,
)

data class RemoveUserRequest(
    val userId: Long,
    val userLoginToDelete: String,
    val teamId: Long,
)