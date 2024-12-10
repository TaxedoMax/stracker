package rtu.mirea.ru.stracker.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import rtu.mirea.ru.stracker.entity.Team
import rtu.mirea.ru.stracker.entity.User

@Repository
interface TeamRepository : JpaRepository<Team, Long>{
    fun findByIdAndLeadId(id: Long, leadId: Long): Team?
}