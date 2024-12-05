package com.sbook.stracker.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sbook.stracker.entity.Team

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamTasksScreen(navController: NavHostController, team: Team) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(team.name) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
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
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
        ) {

        }
    }
}