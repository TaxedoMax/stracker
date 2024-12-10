package rtu.mirea.ru.stracker.entity

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "user_team")
@IdClass(UserTeamId::class)
data class UserTeam(
    @Id
    @Column(name = "user_id")
    val userId: Long = 0,

    @Id
    @Column(name = "team_id")
    val teamId: Long = 0
)

data class UserTeamId(
    val userId: Long = 0,
    val teamId: Long = 0
) : Serializable
