package rtu.mirea.ru.stracker.entity

import jakarta.persistence.*

@Entity
@Table(name = "user_password")
data class UserPassword (
    @Id
    @Column(name = "login")
    val login: String = "Ilya",
    @Column(name = "password")
    val password: String = "123",
)