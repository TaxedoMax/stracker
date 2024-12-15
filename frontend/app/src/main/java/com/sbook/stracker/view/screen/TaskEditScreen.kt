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
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sbook.stracker.entity.TaskType
import com.sbook.stracker.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEditScreen(
    viewModel: TaskViewModel,
    navigateTo: (String) -> Unit,
    navigateBack: () -> Unit,
) {
    val task by viewModel.task
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = if(viewModel.isNewTask) "Новая задача" else "Изменение задачи",
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
            // Поле для ввода названия
            TextField(
                value = task.title,
                onValueChange = {newTitle -> viewModel.onTitleChanged(newTitle) },
                label = { Text("Название") },
                singleLine = true,
                enabled = !viewModel.isLoading.value,
            )

            // Поле для ввода описания
            TextField(
                value = task.description,
                onValueChange = { newDescription -> viewModel.onDescriptionChanged(newDescription) },
                label = { Text("Описание") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 5,
                enabled = !viewModel.isLoading.value,
            )

            // Поле с выбором типа задачи
            Box(
                modifier = Modifier
                    .clickable { viewModel.isTypeExpanded.value = true }
                    .border(
                        BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                        MaterialTheme.shapes.medium
                    )
                    .padding(16.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                ){
                    Text(task.type.text)
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Иконка выпадающего окна"
                    )
                }

                DropdownMenu(
                    expanded = viewModel.isTypeExpanded.value,
                    onDismissRequest = { viewModel.isTypeExpanded.value = false },
                ) {
                    TaskType.entries.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(type.text) },
                            onClick = {
                                viewModel.onTypeChanged(type)
                                viewModel.isTypeExpanded.value = false
                            },
                            enabled = !viewModel.isLoading.value
                        )
                    }
                }
            }

            Spacer(Modifier.weight(1F))

            if(viewModel.isLoading.value){
                CircularProgressIndicator()
            }

            Spacer(Modifier.weight(1F))

            Button(
                enabled = !viewModel.isLoading.value,
                onClick = { viewModel.onAcceptPressed(navigateBack) },
            ) {
                Text("Подтвердить")
            }
        }
    }
}