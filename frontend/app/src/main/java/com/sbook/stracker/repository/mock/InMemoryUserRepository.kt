package com.sbook.stracker.repository.mock

import com.sbook.stracker.dto.user.AuthDTO
import com.sbook.stracker.dto.user.UserDTO
import com.sbook.stracker.entity.User
import com.sbook.stracker.repository.UserRepository
import javax.inject.Inject
class InMemoryUserRepository @Inject constructor(
        private val teamRepository: InMemoryTeamRepository,
    ) : UserRepository {

    private val users = mutableListOf(
        User(id = "1", login = "user1", password = "password1"),
        User(id = "2", login = "user2", password = "password2"),
        User(id = "3", login = "user3", password = "password3")
    )

    override fun getUserById(id: String): UserDTO? {
        val user = users.find { it.id == id }
        return if (user != null) UserDTO(id = user.id, login = user.login) else null
    }

    override fun getUserByLogin(login: String): UserDTO? {
        val user = users.find { it.login == login }
        return if (user != null) UserDTO(id = user.id, login = user.login) else null
    }

    override fun getUsersByTeam(teamId: String): List<UserDTO> {
        val list: List<User> = teamRepository.getUsersByTeam(teamId).map{ userId ->
            users.find {user ->
                user.id == userId
            }!!
        }

        return  list.map{ user -> UserDTO(id = user.id, login = user.login) }
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
        val existingUser = users.find { it.login == authDTO.login }
        return if(existingUser == null || authDTO.password != existingUser.password) "-1"
        else existingUser.id
    }
}
