package rtu.mirea.ru.stracker.controllers

import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import rtu.mirea.ru.stracker.DTO.team.*
import rtu.mirea.ru.stracker.DTO.team.GetTeamRequest
import rtu.mirea.ru.stracker.entity.Team
import rtu.mirea.ru.stracker.services.TeamService

@RestController
@RequestMapping("/v1/team")
class TeamController(
    val teamService: TeamService,
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addTeam(
        @RequestBody createTeamRequest: CreateTeamRequest
    ): CreateTeamResponse {
        try{
            return teamService.createTeam(createTeamRequest)
        } catch (e: EntityNotFoundException){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,e.message)
        } catch (e: EntityNotFoundException){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,e.message)
        } catch (e: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,e.message)
        }

    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    fun addUserToTeam(
        @RequestBody addUserRequest: AddUserRequest
    ): AddUserResponse {
        try {
            return teamService.addUserToTeam(addUserRequest)
        } catch (e: EntityNotFoundException){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,e.message)
        } catch (e: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,e.message)
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getTeams(
        @RequestBody request: GetTeamRequest
    ): GetTeamResponse {
        try {
            return teamService.getTeam(request)
        } catch (e: EntityNotFoundException){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,e.message)
        } catch (e: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,e.message)
        }
    }

    @GetMapping("/{teamId}/users")
    @ResponseStatus(HttpStatus.OK)
    fun getUsers(
        @PathVariable teamId: Long
    ): GetUsersResponse {
        try {
            return teamService.getUsers(teamId)
        } catch (e: EntityNotFoundException){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,e.message)
        } catch (e: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,e.message)
        }
    }

    @DeleteMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    fun deleteUserFromTeam(
        @RequestBody request: DeleteUserRequest
    ): Boolean {
        try {
            return teamService.deleteUser(request)
        } catch (e: EntityNotFoundException){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,e.message)
        } catch (e: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,e.message)
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun editTeam(
        @RequestBody request: EditTeamRequest
    ): Team {
        try {
            return teamService.editTeam(request)
        } catch (e: EntityNotFoundException){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,e.message)
        } catch (e: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,e.message)
        }
    }
}