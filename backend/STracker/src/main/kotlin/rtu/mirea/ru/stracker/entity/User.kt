package rtu.mirea.ru.stracker.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    val login: String = "",
    val photo: String? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
}
