package rtu.mirea.ru.stracker.services

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import rtu.mirea.ru.stracker.DTO.team.*
import rtu.mirea.ru.stracker.DTO.team.GetTeamRequest
import rtu.mirea.ru.stracker.DTO.user.GetTeamsResponse
import rtu.mirea.ru.stracker.DTO.user.TeamsPreview
import rtu.mirea.ru.stracker.entity.Team
import rtu.mirea.ru.stracker.entity.TeamStatus
import rtu.mirea.ru.stracker.entity.UserTeam
import rtu.mirea.ru.stracker.repository.TaskRepository
import rtu.mirea.ru.stracker.repository.TeamRepository
import rtu.mirea.ru.stracker.repository.UserRepository
import rtu.mirea.ru.stracker.repository.UserTeamRepository

@Service("TeamService")
class TeamService(
    private val teamRepository: TeamRepository,
    private val userTeamRepository: UserTeamRepository,
    private val userRepository: UserRepository,
    private val taskRepository: TaskRepository,
) {
    @Transactional
    fun createTeam(
        createTeamRequest: CreateTeamRequest,
    ) : CreateTeamResponse {
        validateCreateTeamRequest(createTeamRequest)
        val team = Team(
            name = createTeamRequest.name,
            leadId = createTeamRequest.leadId,
            description = createTeamRequest.description,
            photo = createTeamRequest.photo,
            status = TeamStatus.ACTIVE,
        )
        val result = teamRepository.save(team)
        userTeamRepository.save(UserTeam(
            userId = result.leadId,
            teamId = result.id
        ))
        return CreateTeamResponse(
            id = result.id,
            name = result.name,
            description = result.description,
            leadId = result.leadId,
            photo = result.photo,
            status = result.status,
        )
    }

    fun validateCreateTeamRequest(request: CreateTeamRequest) {
        val errors = mutableListOf<String>()

        if (request.leadId <= 0) {
            errors.add("id лидера команды не может быть меньше нуля")
        }
        if (request.name.isEmpty()) {
            errors.add("Название команды обязательно для заполнения")
        }

        if (errors.isNotEmpty()) {
            throw IllegalArgumentException(errors.joinToString(separator = "; "))
        }
    }

    fun addUserToTeam(request: AddUserRequest): AddUserResponse{
        if (!isUserLeadOfTeam(request.teamId, request.leadId)){
            throw IllegalArgumentException("Пользователь не является лидером команды")
        }
        val user = userRepository.findByLogin(request.userLogin)
            ?: throw IllegalArgumentException("Добавляемый пользователь не найден")

        userTeamRepository.save(UserTeam(
            userId = user.id,
            teamId = request.teamId,
        ))
        return AddUserResponse(true)
    }

    fun isUserLeadOfTeam(teamId: Long, userId: Long): Boolean {
        val team = teamRepository.findByIdAndLeadId(teamId, userId)
        return team != null
    }

    fun getTeams(id: Long): GetTeamsResponse {
        if (userRepository.findById(id).isEmpty) {
            throw IllegalArgumentException("Пользователь не найден")
        }
        val teamIds = userTeamRepository.findAllByUserId(id)

        return GetTeamsResponse(
            teams = teamIds.map { teamId ->
                val team = teamRepository.findById(teamId.teamId).get()
                TeamsPreview(
                    id = team.id,
                    description = team.description,
                    photo = team.photo,
                    name = team.name,
                    isLeader = isUserLeadOfTeam(team.id, id),
                )
            }
        )
    }

    fun getTeam(request: GetTeamRequest): GetTeamResponse {
        if (!userTeamRepository.existsUserTeamsByUserIdAndTeamId(request.userId, request.teamId)) {
            throw IllegalArgumentException("У данного пользователя нет доступа к данной команде")
        }
        val tasks = taskRepository.findAllByTeamId(request.teamId)
        val team = teamRepository.findById(request.teamId).get()

        return GetTeamResponse(
            id = request.teamId,
            name = team.name,
            photo = team.photo,
            leadId = team.leadId,
            description = team.description,
            status = team.status,
            tasks = tasks.map { task ->
                TaskInTeam(
                    id = task.id,
                    name = task.name,
                    description = task.description,
                    status = task.status.toString(),
                    type = task.type.toString(),
                    authorId = task.authorId,
                    executorLogin = if (task.executorId != null) takeLoginById(task.executorId) else null,
                    teamId = task.teamId,
                )
            },
            isUserLead = isUserLeadOfTeam(request.teamId, request.userId),
        )
    }

    private fun takeLoginById(userId: Long): String {
        return userRepository.findById(userId).get().login
    }

    fun getUsers(teamId: Long): GetUsersResponse{
        val userTeams = userTeamRepository.findAllByTeamId(teamId)
        if (userTeams.isEmpty()){
            throw IllegalArgumentException("Команды с id $teamId не существует")
        }
        return GetUsersResponse(
            users = userTeams.map { userTeam ->
                val user = userRepository.findById(userTeam.userId).get()
                UserInTeam(
                    id = user.id,
                    login = user.login,
                    photo = user.photo,
                    isLead = isUserLeadOfTeam(teamId, user.id),
                )
            }
        )
    }
}