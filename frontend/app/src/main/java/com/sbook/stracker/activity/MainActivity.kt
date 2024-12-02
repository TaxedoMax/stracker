package com.sbook.stracker.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sbook.stracker.repository.UserRepository
import com.sbook.stracker.repository.mock.InMemoryTaskRepository
import com.sbook.stracker.repository.mock.InMemoryUserRepository
import com.sbook.stracker.ui.theme.STrackerTheme
import com.sbook.stracker.view.screen.ProfileScreen
import com.sbook.stracker.view.screen.TeamsScreen
import com.sbook.stracker.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            STrackerTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "teams") {
                    composable("teams") { TeamsScreen(navController) }
                    composable("profile") {
                        ProfileScreen(
                            navController,
                            "1",
                            //UserViewModel(InMemoryUserRepository(), InMemoryTaskRepository())
                        )
                    }
                    composable("team_tasks/{teamId}") { backStackEntry ->
                        val teamId = backStackEntry.arguments?.getString("teamId") ?: ""
                        /* TODO: TeamTasksScreen(navController, teamId) */
                    }
                    composable("task_details/{taskId}") { backStackEntry ->
                        val taskId = backStackEntry.arguments?.getString("taskId") ?: ""
                        /* TODO: TaskDetailsScreen(navController, taskId) */
                    }
                    composable("create_task") { /* TODO: CreateTaskScreen(navController) */ }
                    composable("team_settings/{teamId}") { backStackEntry ->
                        val teamId = backStackEntry.arguments?.getString("teamId") ?: ""
                        /* TODO: TeamSettingsScreen(navController, teamId) */
                    }

                }
            }
        }
    }
}