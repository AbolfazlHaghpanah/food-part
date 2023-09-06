package com.example.foodpart.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun FoodPartTextField(
    textFieldState: MutableState<String>,
    label: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = textFieldState.value,
        onValueChange = { it -> textFieldState.value = it },
        modifier = modifier
            .fillMaxWidth(),
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
        shape = MaterialTheme.shapes.medium,
        textStyle = MaterialTheme
            .typography
            .subtitle1
            .copy(textAlign = TextAlign.Start)
    )

}