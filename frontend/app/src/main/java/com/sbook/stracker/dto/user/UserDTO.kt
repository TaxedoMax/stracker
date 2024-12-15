package com.sbook.stracker.dto.user

data class UserDTO(
    val id: Long,
    val login: String,
    val photo: String = "",
)