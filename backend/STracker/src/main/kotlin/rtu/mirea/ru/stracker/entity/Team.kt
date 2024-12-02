package rtu.mirea.ru.stracker.entity

import jakarta.persistence.*

@Entity
@Table(name = "teams")
data class Team(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    @Column(name = "lead_id")
    val leadId: Long? = null,
    val description: String? = null
) {
    constructor() : this(0, "Ilya")
}
