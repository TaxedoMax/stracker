package rtu.mirea.ru.stracker.entity

import jakarta.persistence.*

@Entity
@Table(name = "user_team")
data class UserTeam(
    @Id
    @Column(name = "user_id")
    val userId: Long,

    @Id
    @Column(name = "team_id")
    val teamId: Long
) {
    constructor() : this(0,0)
}
