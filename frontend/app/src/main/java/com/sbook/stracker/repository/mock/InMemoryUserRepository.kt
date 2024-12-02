package com.sbook.stracker.repository.mock

import com.sbook.stracker.entity.User
import com.sbook.stracker.repository.UserRepository

class InMemoryUserRepository : UserRepository {
    private val users = mutableListOf(
        User(id = "1", login = "user1", password = "password1"),
        User(id = "2", login = "user2", password = "password2"),
        User(id = "3", login = "user3", password = "password3")
    )

    override fun getUserById(id: String): User? = users.find { it.id == id }

    override fun getUserByLogin(login: String): User? = users.find { it.login == login }

    override fun addUser(user: User): Boolean {
        return users.add(user)
    }

    override fun updateUser(user: User): Boolean {
        val index = users.indexOfFirst { it.id == user.id }
        if (index == -1) return false
        users[index] = user
        return true
    }
}
