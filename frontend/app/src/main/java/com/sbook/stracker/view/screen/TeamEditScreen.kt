package com.sbook.stracker.view.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.sharp.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sbook.stracker.dto.user.UserDTO
import com.sbook.stracker.ui.theme.Blue
import com.sbook.stracker.ui.theme.DarkBlue
import com.sbook.stracker.ui.theme.LightBlue
import com.sbook.stracker.viewmodel.TeamEditViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamEditScreen(
    editViewModel: TeamEditViewModel,
    navigateTo: (route: String) -> Unit,
    navigateBack: () -> Unit,
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = if(editViewModel.teamId == null) "Новая команда" else "Изменение команды",
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
    ) { padding ->
        // Dialog window, info about warnings
        if(editViewModel.isDialogOpened.value){
            AlertDialog(
                text = { Text(text = editViewModel.dialogText.value) },
                onDismissRequest = {  },
                confirmButton = {
                    Button(onClick = { editViewModel.closeDialog() }) {
                        Text("Ок")
                    }
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            // Team name
            TextField(
                value = editViewModel.name.value,
                onValueChange = {newName -> editViewModel.onNameChanged(newName) },
                label = { Text("Название") },
                colors = TextFieldDefaults.colors(
                    cursorColor = LightBlue,
                    focusedPlaceholderColor = LightBlue.copy(alpha = 0.7f),
                    focusedContainerColor = Blue,
                    unfocusedContainerColor = LightBlue.copy(alpha = 0.7f),
                    focusedLabelColor = LightBlue,
                ),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Список участников:"
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier
                .weight(1F)
                .align(Alignment.CenterHorizontally)
            ) {
                itemsIndexed(editViewModel.usersList.value){ index, user ->
                    UserItem(
                        user = user,
                        isRemovable = user.id != editViewModel.ownerId,
                        onRemove = {
                            editViewModel.removeUser(index)
                        },
                    )
                }
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Blue),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp, horizontal = 8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(16.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Row {
                                TextField(
                                    modifier = Modifier.width(250.dp),
                                    value = editViewModel.newUserLogin.value,
                                    onValueChange = { newLogin ->
                                        editViewModel.onNewUserLoginChanged(
                                            newLogin
                                        )
                                    },
                                    colors = TextFieldDefaults.colors(
                                        cursorColor = LightBlue,
                                        focusedPlaceholderColor = Color.White,
                                        focusedContainerColor = DarkBlue,
                                        unfocusedContainerColor = LightBlue,
                                        focusedLabelColor = LightBlue,
                                    ),
                                )

                                Spacer(modifier = Modifier.width(16.dp).weight(1F))

                                IconButton(
                                    modifier = Modifier
                                        .clickable { !editViewModel.isUserLoading.value },
                                    onClick = { editViewModel.addUser() },
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = "Добавить пользователя"
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Button(
                modifier = Modifier.clickable { editViewModel.isSomethingLoading },
                onClick = { editViewModel.onConfirmButtonClick(navigateBack) }
            ) {
                Text("Подтвердить")
            }
        }
    }
}

@Composable
fun UserItem(user: UserDTO, isRemovable: Boolean, onRemove: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Blue),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row (verticalAlignment = Alignment.CenterVertically){
                Text(
                    text = user.login,
                    modifier = Modifier.weight(1f),
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Start
                )

                if(isRemovable){
                    IconButton(onClick = { onRemove() }) {
                        Icon(
                            imageVector = Icons.Sharp.Delete,
                            contentDescription = "Удалить пользователя",
                        )
                    }
                } else{
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Глава"
                        )
                    }
                }

            }
        }
    }
}