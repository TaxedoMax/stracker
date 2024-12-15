package com.sbook.stracker.entity

data class User(
    val id: Long,
    val login: String,
    val password: String,
    val photo: String = "",
)