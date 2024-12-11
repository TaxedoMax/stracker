package com.sbook.stracker.view.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sbook.stracker.dto.team.TeamForUserDTO
import com.sbook.stracker.viewmodel.TeamsViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamsScreen(
    navController: NavController,
    teamViewModel: TeamsViewModel
) {
    val teams by teamViewModel.userTeams
    val isDataLoading by teamViewModel.isDataLoading

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ваши команды") },
                actions = {
                    IconButton(onClick = { navController.navigate("profile")}) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Профиль"
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier.weight(1F),
                contentAlignment = Alignment.Center,
            ){
                if(!isDataLoading) {
                    LazyColumn(modifier = Modifier.align(Alignment.TopCenter)) {
                        items(teams) { team ->
                            Box(Modifier.padding(10.dp)) {
                                TeamItem(team = team, navController = navController)
                            }
                        }
                    }
                } else{
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = ProgressIndicatorDefaults.circularColor,
                        strokeWidth = ProgressIndicatorDefaults.CircularStrokeWidth,
                        trackColor = ProgressIndicatorDefaults.circularDeterminateTrackColor,
                        strokeCap = ProgressIndicatorDefaults.CircularIndeterminateStrokeCap
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("create_team") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Создать новую команду")
            }
        }
    }
}

@Composable
fun TeamItem(team: TeamForUserDTO, navController: NavController) {
    Button(
        onClick = { navController.navigate("team/${Json.encodeToString(team)}/tasks") },
    ) {
        Row {
            Text(
                text = team.name,
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1F),
                style = MaterialTheme.typography.bodyLarge
            )
            if (team.isOwner) {
                IconButton(onClick = { navController.navigate("team/${team.id}/edit") }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Редактировать"
                    )
                }
            }
        }

    }
}
