package com.sbook.stracker.dto.user

data class AddUserRequest(
    val leadId: Long,
    val userLogin: String,
    val teamId: Long,
)