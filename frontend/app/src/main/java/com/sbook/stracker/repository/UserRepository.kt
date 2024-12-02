package com.sbook.stracker.repository

import com.sbook.stracker.entity.User

interface UserRepository {
    fun getUserById(id: String): User?
    fun getUserByLogin(login: String): User?
    fun addUser(user: User): Boolean
    fun updateUser(user: User): Boolean
}

