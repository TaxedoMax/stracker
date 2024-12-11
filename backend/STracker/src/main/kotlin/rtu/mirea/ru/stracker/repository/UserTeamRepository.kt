package rtu.mirea.ru.stracker.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import rtu.mirea.ru.stracker.entity.User
import rtu.mirea.ru.stracker.entity.UserTeam
import rtu.mirea.ru.stracker.entity.UserTeamId

@Repository
interface UserTeamRepository : JpaRepository<UserTeam, UserTeamId>{
    fun findAllByUserId(userId: Long): List<UserTeam>
    fun findAllByTeamId(teamId: Long): List<UserTeam>
    fun existsUserTeamsByUserIdAndTeamId(userId: Long, teamId: Long): Boolean
    fun removeByUserIdAndTeamId(userId: Long, teamId: Long)
}