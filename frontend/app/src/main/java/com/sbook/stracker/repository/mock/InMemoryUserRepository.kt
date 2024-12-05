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

    override fun registerUser(user: User): String {
        var newUserId = "-1"
        if(getUserByLogin(user.login) == null){
            val newUser = user.copy(id = users.size.toString())
            users.add(newUser)
            newUserId = newUser.id
        }
        return newUserId
    }

    override fun updateUser(user: User): Boolean {
        val index = users.indexOfFirst { it.id == user.id }
        if (index == -1) return false
        users[index] = user
        return true
    }

    override fun login(user: User): String {
        val existingUser = getUserByLogin(user.login)
        return if(existingUser == null || user.password != existingUser.password) "-1"
        else existingUser.id
    }
}
