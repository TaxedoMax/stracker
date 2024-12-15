package com.sbook.stracker.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sbook.stracker.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    viewModel: TaskViewModel,
    navigateTo: (String) -> Unit,
    navigateBack: () -> Unit,
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = "Задача",
                ) },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                },
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
    ) {padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Заголовок задачи
            Text(
                text = viewModel.task.value.title,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )

            // Тип задачи
            InfoRow(
                label = "Тип задачи",
                value = viewModel.task.value.type.text,
                isLoading = viewModel.isLoading.value
            )

            // Логин создателя задачи
            InfoRow(
                label = "Создатель",
                value = viewModel.owner.value?.login,
                isLoading = viewModel.isLoading.value
            )

            // Логин исполнителя задачи
            InfoRow(
                label = "Исполнитель",
                value = viewModel.executor.value?.login,
                isLoading = viewModel.isLoading.value
            )

            // Статус задачи
            InfoRow(
                label = "Статус",
                value = viewModel.task.value.status.text,
                isLoading = viewModel.isLoading.value
            )

            // Описание задачи
            Text(
                text = "Описание",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = viewModel.task.value.description,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth(),
            )

            if(viewModel.isLoading.value){
                CircularProgressIndicator(Modifier.weight(1F))
            } else if(viewModel.executor.value == null){
                Spacer(Modifier.weight(1F))
                Button(onClick = { viewModel.setExecutor() }) {
                    Text("Взять задачу")
                }
            } else if(viewModel.executor.value?.id == viewModel.userId){
                Spacer(Modifier.weight(1F))
                Button(onClick = { viewModel.removeExecutor() }) {
                    Text(
                        text = "Отказаться от задачи",
                        color = Color.Red,
                    )
                }
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String?, isLoading: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = if(isLoading && value == null) "Загрузка.." else value ?: "Не указано",
            style = MaterialTheme.typography.bodySmall
        )
    }
}