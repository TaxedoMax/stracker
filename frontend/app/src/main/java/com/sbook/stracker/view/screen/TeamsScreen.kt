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
import com.sbook.stracker.dto.team.TeamResponse
import com.sbook.stracker.viewmodel.TeamsViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamsScreen(
    teamViewModel: TeamsViewModel,
    navigateTo: (route: String) -> Unit,
) {
    val teams by teamViewModel.userTeams
    val isDataLoading by teamViewModel.isDataLoading

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ваши команды") },
                actions = {
                    IconButton(onClick = { navigateTo("profile")}) {
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
                                TeamItem(team = team, navigateTo = navigateTo)
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
                onClick = { navigateTo("create_team") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Создать новую команду")
            }
        }
    }
}

@Composable
fun TeamItem(team: TeamResponse, navigateTo: (route: String) -> Unit) {
    Button(
        onClick = { navigateTo("team/${Json.encodeToString(team)}/tasks") },
    ) {
        Row {
            Text(
                text = team.name,
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1F),
                style = MaterialTheme.typography.bodyLarge
            )
            if (team.isUserLead) {
                IconButton(onClick = { navigateTo("team/${team.id}/edit") }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Редактировать"
                    )
                }
            }
        }

    }
}
