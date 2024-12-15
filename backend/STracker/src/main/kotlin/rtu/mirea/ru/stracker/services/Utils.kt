package rtu.mirea.ru.stracker.services

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import rtu.mirea.ru.stracker.repository.TeamRepository
import rtu.mirea.ru.stracker.repository.UserRepository
import rtu.mirea.ru.stracker.repository.UserTeamRepository

@Service
class Utils(
    private val userRepository: UserRepository,
    private val teamRepository: TeamRepository,
    private val userTeamRepository: UserTeamRepository,
) {
    fun isUserExistByLogin(username: String): Boolean {
        val user = userRepository.findByLogin(username)
        return user != null
    }

    fun isUserExistById(id: Long): Boolean{
        val user = userRepository.findById(id).orElse(null)
        return user != null
    }

    fun isUserLeadOfTeam(teamId: Long, userId: Long): Boolean {
        val team = teamRepository.findByIdAndLeadId(teamId, userId)
        return team != null
    }

    fun takeLoginById(userId: Long): String {
        return userRepository.findById(userId).get().login
    }

    fun takeIdByLogin(login: String): Long {
        return userRepository.findByLogin(login)?.id ?: throw EntityNotFoundException("пользователь не найден")
    }

    fun isUserInTeam(teamId: Long, userId: Long): Boolean {
        return userTeamRepository.existsUserTeamsByUserIdAndTeamId(userId, teamId)
    }
}