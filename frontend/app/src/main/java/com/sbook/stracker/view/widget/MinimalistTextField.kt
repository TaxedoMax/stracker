package com.sbook.stracker.view.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import com.sbook.stracker.ui.theme.Blue
import com.sbook.stracker.ui.theme.LightBlue

@Composable
fun MinimalistTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "",
    modifier: Modifier = Modifier,
    placeholder: String = "",
    singleLine: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        modifier = modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = LightBlue,
            unfocusedBorderColor = Blue,
            cursorColor = LightBlue,
            focusedPlaceholderColor = LightBlue.copy(alpha = 0.7f),
            unfocusedContainerColor = LightBlue.copy(alpha = 0.7f),
            focusedLabelColor = LightBlue,
        ),
        shape = OutlinedTextFieldDefaults.shape,
        singleLine = singleLine,
        visualTransformation = visualTransformation
    )
}