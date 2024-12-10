package com.sbook.stracker.repository.mock

import com.sbook.stracker.dto.AuthDTO
import com.sbook.stracker.entity.User
import com.sbook.stracker.repository.UserRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
class InMemoryUserRepository @Inject constructor(
        private val teamRepository: InMemoryTeamRepository,
    ) : UserRepository {

    private val users = mutableListOf(
        User(id = "1", login = "user1", password = "password1"),
        User(id = "2", login = "user2", password = "password2"),
        User(id = "3", login = "user3", password = "password3")
    )

    override fun getUserById(id: String): User? = users.find { it.id == id }

    override fun getUserByLogin(login: String): User? = users.find { it.login == login }

    override fun getUsersByTeam(teamId: String): List<User> {
        return teamRepository.getUsersByTeam(teamId).map{ userId ->
            users.find {user ->
                user.id == userId
            }!!
        }
    }

    override fun registerUser(authDTO: AuthDTO): String {
        var userId = "-1"
        if(getUserByLogin(authDTO.login) == null){
            val newUser = User(
                id = (users.size + 1).toString(),
                login = authDTO.login,
                password = authDTO.password,
            )
            users.add(newUser)
            userId = newUser.id
        }
        return userId
    }

    override fun updateUser(user: User): Boolean {
        val index = users.indexOfFirst { it.id == user.id }
        if (index == -1) return false
        users[index] = user
        return true
    }

    override fun login(authDTO: AuthDTO): String {
        val existingUser = getUserByLogin(authDTO.login)
        return if(existingUser == null || authDTO.password != existingUser.password) "-1"
        else existingUser.id
    }
}
