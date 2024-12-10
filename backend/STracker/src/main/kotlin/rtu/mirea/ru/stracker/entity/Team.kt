package rtu.mirea.ru.stracker.entity

import jakarta.persistence.*

@Entity
@Table(name = "teams")
data class Team(
    val name: String = "",
    @Column(name = "lead_id")
    val leadId: Long = 0,
    val description: String? = null,
    val photo: String? = null,
    val status: TeamStatus = TeamStatus.ACTIVE,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
}

enum class TeamStatus {
    ACTIVE,
    ARCHIVE,
}
