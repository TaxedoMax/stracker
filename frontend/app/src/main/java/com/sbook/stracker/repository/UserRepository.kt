package com.sbook.stracker.repository

import com.sbook.stracker.entity.User

interface UserRepository {
    fun getUserById(id: String): User?
    fun getUserByLogin(login: String): User?
    fun registerUser(user: User): String
    fun updateUser(user: User): Boolean
    fun login(user: User): String
}

