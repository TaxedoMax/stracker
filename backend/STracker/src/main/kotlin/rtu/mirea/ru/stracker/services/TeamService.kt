package rtu.mirea.ru.stracker.services

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import rtu.mirea.ru.stracker.DTO.team.AddUserRequest
import rtu.mirea.ru.stracker.DTO.team.AddUserResponse
import rtu.mirea.ru.stracker.DTO.team.CreateTeamRequest
import rtu.mirea.ru.stracker.DTO.team.CreateTeamResponse
import rtu.mirea.ru.stracker.entity.Team
import rtu.mirea.ru.stracker.entity.TeamStatus
import rtu.mirea.ru.stracker.entity.UserTeam
import rtu.mirea.ru.stracker.repository.TeamRepository
import rtu.mirea.ru.stracker.repository.UserRepository
import rtu.mirea.ru.stracker.repository.UserTeamRepository

@Service("TeamService")
class TeamService(
    val teamRepository : TeamRepository,
    val userTeamRepository: UserTeamRepository,
    val userRepository: UserRepository,
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
}