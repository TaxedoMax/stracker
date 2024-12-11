package com.sbook.stracker.view.widget

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sbook.stracker.ViewModelFactoryProvider
import com.sbook.stracker.dto.team.TeamForUserDTO
import com.sbook.stracker.view.screen.LoginScreen
import com.sbook.stracker.view.screen.ProfileScreen
import com.sbook.stracker.view.screen.RegistrationScreen
import com.sbook.stracker.view.screen.TeamEditScreen
import com.sbook.stracker.view.screen.TeamTasksScreen
import com.sbook.stracker.view.screen.TeamsScreen
import com.sbook.stracker.viewmodel.UserViewModel
import dagger.hilt.android.EntryPointAccessors
import kotlinx.serialization.json.Json

@Composable
fun MainNavHost(navController: NavHostController){
    val userViewModel: UserViewModel = hiltViewModel()
    val factoryProvider = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        ViewModelFactoryProvider::class.java
    )

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
        composable("profile") {
            ProfileScreen(
                navController = navController,
                userViewModel = userViewModel
            )
        }
        composable("teams") {
            val teamViewModel = factoryProvider.teamViewModelFactory().create(userViewModel.userId)
            TeamsScreen(
                navController = navController,
                teamViewModel = teamViewModel
                )
        }
        composable("team/{teamId}/edit"){navBackStackEntry ->
            val teamId = navBackStackEntry.arguments?.getString("teamId") ?: ""
            TeamEditScreen(
                navController = navController,
                editViewModel = factoryProvider.teamEditViewModelFactory()
                    .create(userViewModel.userId, teamId)
            )
        }
        composable("team/{team}/tasks") { backStackEntry ->
            val teamString = backStackEntry.arguments?.getString("team") ?: ""
            val team = Json.decodeFromString<TeamForUserDTO>(teamString)
            TeamTasksScreen(
                navController = navController,
                viewModel = factoryProvider.teamTasksViewModelFactory().create(team),
            )
        }
        composable("task_details/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId") ?: ""
            /* TODO: TaskDetailsScreen(navController, taskId) */
        }
        composable("create_task") { /* TODO: CreateTaskScreen(navController) */ }
        composable("create_team"){
            TeamEditScreen(
                navController = navController,
                editViewModel = factoryProvider.teamEditViewModelFactory()
                    .create(userViewModel.userId, null)
                )
        }
        composable("team_settings/{teamId}") { backStackEntry ->
            val teamId = backStackEntry.arguments?.getString("teamId") ?: ""
            /* TODO: TeamSettingsScreen(navController, teamId) */
        }
    }
}