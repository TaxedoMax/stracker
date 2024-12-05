package com.sbook.stracker

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sbook.stracker.view.screen.LoginScreen
import com.sbook.stracker.view.screen.ProfileScreen
import com.sbook.stracker.view.screen.RegistrationScreen
import com.sbook.stracker.view.screen.TeamCreationScreen
import com.sbook.stracker.view.screen.TeamsScreen
import com.sbook.stracker.viewmodel.UserViewModel

@Composable
fun MainNavHost(navController: NavHostController){
    val userViewModel: UserViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = "login"){
        composable("login"){
            LoginScreen(
                navController = navController,
                userViewModel = userViewModel
                )
        }
        composable("register"){
            RegistrationScreen(
                navController = navController,
                userViewModel = userViewModel
                )
        }
        composable("teams") {
            TeamsScreen(
                navController = navController,
                userViewModel = userViewModel
                )
        }
        composable("profile") {
            ProfileScreen(
                navController = navController,
                userViewModel = userViewModel
            )
        }
        composable("team_tasks/{teamId}") { backStackEntry ->
            val teamId = backStackEntry.arguments?.getString("teamId") ?: ""
            // TODO: TeamTasksScreen(navController, teamId)
        }
        composable("task_details/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId") ?: ""
            /* TODO: TaskDetailsScreen(navController, taskId) */
        }
        composable("create_task") { /* TODO: CreateTaskScreen(navController) */ }
        composable("create_team"){ TeamCreationScreen(navController = navController) }
        composable("team_settings/{teamId}") { backStackEntry ->
            val teamId = backStackEntry.arguments?.getString("teamId") ?: ""
            /* TODO: TeamSettingsScreen(navController, teamId) */
        }
    }
}