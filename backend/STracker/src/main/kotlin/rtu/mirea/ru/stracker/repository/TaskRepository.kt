package rtu.mirea.ru.stracker.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import rtu.mirea.ru.stracker.entity.Task

@Repository
interface TaskRepository : JpaRepository<Task, Long>{
    fun findAllByTeamId(teamId: Long): List<Task>
    fun findAllByExecutorId(executorId: Long): List<Task>
    fun findAllByExecutorIdAndTeamId(executorId: Long, teamId: Long): List<Task>
}