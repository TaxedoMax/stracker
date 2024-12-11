package com.sbook.stracker.view.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sbook.stracker.view.widget.TaskItem
import com.sbook.stracker.viewmodel.TeamTasksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamTasksScreen(navController: NavHostController, viewModel: TeamTasksViewModel) {
    val teamName = viewModel.team.name
    val tasks by viewModel.tasksList
    val isDataLoading by viewModel.isDataLoading

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(teamName) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                },
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
    ){ padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if(isDataLoading){
                Box(modifier = Modifier.weight(1F),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            } else{
                LazyColumn(Modifier.weight(1F)) {
                    items(tasks){ task ->
                        TaskItem(task = task)
                    }
                }
            }
        }
    }
}

