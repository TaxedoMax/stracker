package com.sbook.stracker.view.widget

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sbook.stracker.ViewModelFactoryProvider
import com.sbook.stracker.dto.team.TeamResponseDTO
import com.sbook.stracker.view.screen.LoginScreen
import com.sbook.stracker.view.screen.ProfileScreen
import com.sbook.stracker.view.screen.RegistrationScreen
import com.sbook.stracker.view.screen.TaskEditScreen
import com.sbook.stracker.view.screen.TaskScreen
import com.sbook.stracker.view.screen.TeamEditScreen
import com.sbook.stracker.view.screen.TeamTasksScreen
import com.sbook.stracker.view.screen.TeamsScreen
import com.sbook.stracker.viewmodel.UserViewModel
import dagger.hilt.android.EntryPointAccessors
import kotlinx.serialization.json.Json

@Composable
fun MainNavHost(){
    val navController = rememberNavController()
    val navigateTo = {route: String -> navController.navigate(route)}
    val navigateBack: () -> Unit = { navController.popBackStack() }

    val userViewModel: UserViewModel = hiltViewModel()
    val factoryProvider = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        ViewModelFactoryProvider::class.java
    )

    NavHost(navController = navController, startDestination = "login"){
        composable("login"){
            LoginScreen(
                userViewModel = remember{ userViewModel },
                navigateTo = navigateTo,
                )
        }
        composable("register"){
            RegistrationScreen(
                userViewModel = remember { userViewModel },
                navigateTo = navigateTo,
                )
        }
        composable("profile") {
            userViewModel.loadUserTasks()
            ProfileScreen(
                userViewModel = remember { userViewModel },
                navigateTo = navigateTo,
                navigateBack = navigateBack,
            )
        }
        composable("teams") {
            val teamViewModel = factoryProvider.teamViewModelFactory().create(userViewModel.userId)
            TeamsScreen(
                teamViewModel = remember { teamViewModel },
                navigateTo = navigateTo,
                )
        }
        composable("team/{teamId}/edit"){navBackStackEntry ->
            val teamId = navBackStackEntry.arguments?.getString("teamId") ?: ""
            TeamEditScreen(
                editViewModel = remember { factoryProvider.teamEditViewModelFactory()
                    .create(userViewModel.userId, teamId) },
                navigateTo = navigateTo,
                navigateBack = navigateBack,
            )
        }
        composable("team/{team}/tasks") { backStackEntry ->
            val teamString = backStackEntry.arguments?.getString("team") ?: ""
            val team = Json.decodeFromString<TeamResponseDTO>(teamString)
            TeamTasksScreen(
                viewModel = remember { factoryProvider.teamTasksViewModelFactory()
                    .create(userViewModel.userId, team) },
                navigateTo = navigateTo,
                navigateBack = navigateBack,
            )
        }
        composable("team/{team}/new_task"){ backStackEntry ->
            val teamString = backStackEntry.arguments?.getString("team") ?: ""
            val team = Json.decodeFromString<TeamResponseDTO>(teamString)
            TaskEditScreen(
                viewModel = remember { factoryProvider.taskEditViewModelFactory()
                    .create(teamInit = team, userId = userViewModel.userId,) },
                navigateTo = navigateTo,
                navigateBack = navigateBack,
            )
        }
        composable("task/{taskId}/edit"){ backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")!!
            TaskEditScreen(
                viewModel = factoryProvider.taskEditViewModelFactory()
                    .create(userId = userViewModel.userId, taskId = taskId),
                navigateTo = navigateTo,
                navigateBack = navigateBack,
            )
        }
        composable("task/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId") ?: ""
            TaskScreen(
                viewModel = remember { factoryProvider.taskEditViewModelFactory()
                    .create(userId = userViewModel.userId, taskId = taskId) },
                navigateTo = navigateTo,
                navigateBack = navigateBack,
            )
        }
        composable("create_team"){
            TeamEditScreen(
                editViewModel = remember { factoryProvider.teamEditViewModelFactory()
                    .create(userViewModel.userId, null) },
                navigateTo = navigateTo,
                navigateBack = navigateBack,
                )
        }
    }
}