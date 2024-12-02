package com.sbook.stracker.entity

data class Task(
    val id: String,
    val ownerId: String,
    val title: String,
    val description: String,
    val status: String
)