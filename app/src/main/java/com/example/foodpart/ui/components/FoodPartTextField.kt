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
import androidx.compose.ui.unit.dp

@Composable
fun foodPartTextField(
    textFieldState: MutableState<String>,
    label: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = textFieldState.value,
        onValueChange = { it -> textFieldState.value = it },
        modifier = modifier
            .fillMaxWidth()
            .height(85.dp),
        placeholder = {
            Text(
                text = label,
                style = MaterialTheme.typography.subtitle1
            )

        },
        colors = TextFieldDefaults
            .textFieldColors(focusedIndicatorColor = MaterialTheme.colors.background),
        shape = MaterialTheme.shapes.medium
    )

}