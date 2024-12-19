package com.sbook.stracker.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.*
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.sharp.ExitToApp
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.sharp.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.sbook.stracker.R
import com.sbook.stracker.entity.Task
import com.sbook.stracker.view.widget.TaskItem
import com.sbook.stracker.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    userViewModel: UserViewModel,
    navigateTo: (route: String) -> Unit,
    navigateBack: () -> Unit,
) {
    val user by userViewModel.user
    val userTasks by userViewModel.userTasks
    val isDataLoading by userViewModel.isDataLoading

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Sharp.ExitToApp,
                            contentDescription = "Log out",
                            tint = Color.Red,
                        )
                    }
                },
                title = { Text("Профиль") }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(isDataLoading){
                Box(
                    modifier = Modifier.weight(1F),
                    contentAlignment = Alignment.Center,
                ){
                    CircularProgressIndicator()
                }
            } else{
                Image(
                    painter = painterResource(id = R.drawable.no_photo),
                    contentDescription = "Logo",
                    alignment = Alignment.Center,
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = user?.login ?: "Ошибка",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Ваши задачи:",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Список задач пользователя
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(userTasks.size) { index ->
                        TaskItem(task = userTasks[index], userId = user!!.id, navigateTo = navigateTo)
                    }
                }
            }
        }
    }
}
