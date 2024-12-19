package com.sbook.stracker.view.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sbook.stracker.entity.TaskStatus
import com.sbook.stracker.entity.TaskType
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
                textValue = viewModel.task.value.type.text,
                isLoading = viewModel.isLoading.value,
                isText = true,
            )

            // Логин создателя задачи
            InfoRow(
                label = "Создатель",
                textValue = viewModel.owner.value?.login,
                isLoading = viewModel.isLoading.value,
                isText = true,
            )

            // Логин исполнителя задачи
            InfoRow(
                label = "Исполнитель",
                textValue = viewModel.executor.value?.login,
                isLoading = viewModel.isLoading.value,
                isText = true,
            )

            // Статус задачи
            InfoRow(
                label = "Статус",
                textValue = viewModel.task.value.status.text,
                composableValue = {
                    Box(
                        modifier = Modifier
                            .clickable { viewModel.isStatusExpanded.value = true }
                            .border(
                                BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                                MaterialTheme.shapes.medium
                            )
                            .padding(16.dp),
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ){
                            Text(viewModel.task.value.status.text)
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Иконка выпадающего окна"
                            )
                        }

                        DropdownMenu(
                            expanded = viewModel.isStatusExpanded.value,
                            onDismissRequest = { viewModel.isStatusExpanded.value = false },
                        ) {
                            TaskStatus.entries.forEach { status ->
                                DropdownMenuItem(
                                    text = { Text(status.text) },
                                    onClick = {
                                        viewModel.onStatusChanged(status)
                                        viewModel.isStatusExpanded.value = false
                                    },
                                    enabled = !viewModel.isLoading.value
                                )
                            }
                        }
                    }
                },
                isLoading = viewModel.isLoading.value,
                isText = false,
            )

            // Описание задачи
            Text(
                text = "Описание",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Left,
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
fun InfoRow(
    label: String,
    textValue: String?,
    composableValue: @Composable ()->Unit = {},
    isText: Boolean,
    isLoading: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
        if(isText){
            Text(
                text = if(isLoading && textValue == null) "Загрузка.." else textValue ?: "Не указано",
                style = MaterialTheme.typography.bodyMedium
            )
        } else{
            composableValue()
        }
    }
}