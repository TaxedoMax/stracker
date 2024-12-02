package rtu.mirea.ru.stracker.entity

import jakarta.persistence.*

@Entity
@Table(name = "tasks")
class Task(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    val status: TaskStatus,
    val type: String,
    val description: String,
    @Column(name = "author_id")
    val authorId: Long,
    @Column(name = "executor_id")
    val executorId: Long,
    @Column(name = "team_id")
    val teamId: Long,
){
    constructor() : this(0, "", TaskStatus.OPEN, "", "", 0, 0, 0)
}

enum class TaskStatus {
    OPEN,
    CLOSE,
    IN_PROGRESS,
    NEED_INFORMATION,
    ON_CHECK,
}
