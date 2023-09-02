package com.example.foodpart.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun foodPartTextField(
    textFieldState: MutableState<String>,
    label: String,
    height: Dp,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = textFieldState.value,
        onValueChange = { it -> textFieldState.value = it },
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        placeholder = {
            Text(
                text = label,
                style = MaterialTheme.typography.subtitle1
            )

        },
        colors = TextFieldDefaults
            .textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                focusedIndicatorColor = MaterialTheme.colors.background,
                unfocusedIndicatorColor = MaterialTheme.colors.background,
                placeholderColor = MaterialTheme.colors.onBackground
            ),
        shape = MaterialTheme.shapes.medium
    )

}