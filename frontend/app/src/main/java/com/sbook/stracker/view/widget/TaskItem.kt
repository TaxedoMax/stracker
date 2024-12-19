package com.sbook.stracker.view.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.sbook.stracker.entity.Task
import com.sbook.stracker.ui.theme.Blue
import com.sbook.stracker.ui.theme.LightBlue

@Composable
fun TaskItem(task: Task, userId: String, navigateTo: (route: String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { navigateTo("task/${task.id}") },
        colors = CardDefaults.cardColors(
            containerColor = Blue,
        )
    ) {
        Row (verticalAlignment = Alignment.CenterVertically){
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1F),
            ) {
                Text(text = task.title, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = task.type.text, style = MaterialTheme.typography.labelMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Статус: ${task.status.text}",
                    style = MaterialTheme.typography.labelSmall
                )
            }

            if(userId == task.ownerId) IconButton(onClick = { navigateTo("task/${task.id}/edit") }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Редактировать задачу"
                )
            }
        }
    }
}