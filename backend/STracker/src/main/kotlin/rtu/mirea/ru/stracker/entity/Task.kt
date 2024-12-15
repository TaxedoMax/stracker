package rtu.mirea.ru.stracker.entity

import jakarta.persistence.*

@Entity
@Table(name = "tasks")
class Task(
    val name: String = "name",
    val status: TaskStatus = TaskStatus.OPEN,
    val type: TaskType = TaskType.TASK,
    val description: String? = null,
    @Column(name = "author_id")
    val authorId: Long = 0,
    @Column(name = "executor_id")
    var executorId: Long? = null,
    @Column(name = "team_id")
    val teamId: Long = 0,
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}

enum class TaskStatus {
    OPEN,
    CLOSE,
    IN_PROGRESS,
    NEED_INFORMATION,
    ON_CHECK,
}

enum class TaskType {
    HANDMADE,
    BUYING,
    CREATION,
    TASK,
}
