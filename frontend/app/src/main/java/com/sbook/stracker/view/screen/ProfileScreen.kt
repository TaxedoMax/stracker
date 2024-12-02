package com.sbook.stracker.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sbook.stracker.entity.Task
import com.sbook.stracker.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, userId: String) {
    val viewModel: UserViewModel = hiltViewModel()
    val user = viewModel.getUser(userId) // Получаем данные пользователя
    val userTasks = viewModel.getUserTasks(userId) // Получаем задачи пользователя

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Назад"
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
//            Image(
//                painter = painterResource(id = R.drawable.profile_placeholder), // Замените на реальное изображение
//                contentDescription = "Фото профиля",
//                modifier = Modifier
//                    .size(120.dp)
//                    .clip(CircleShape)
//                    .background(MaterialTheme.colors.primary)
//            )
            Box(modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .background(Color.Black)
            )


            Spacer(modifier = Modifier.height(16.dp))

            // Логин пользователя
            Text(
                text = user?.login ?: "Логин отсутствует",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Заголовок списка задач
            Text(
                text = "Ваши задачи:",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Список задач пользователя
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(userTasks.size) { index ->
                    TaskItem(task = userTasks[index])
                }
            }
        }
    }
}

@Composable
fun TaskItem(task: Task) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        //elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = task.title, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = task.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Статус: ${task.status}", style = MaterialTheme.typography.labelSmall)
        }
    }
}
