package rtu.mirea.ru.stracker.controllers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import rtu.mirea.ru.stracker.DTO.team.AddUserRequest
import rtu.mirea.ru.stracker.DTO.team.AddUserResponse
import rtu.mirea.ru.stracker.DTO.team.CreateTeamRequest
import rtu.mirea.ru.stracker.DTO.team.CreateTeamResponse
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
        } catch (e: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,e.message)
        }
    }
}