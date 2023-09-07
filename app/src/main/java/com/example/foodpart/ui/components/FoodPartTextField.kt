package com.example.foodpart.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
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
    modifier: Modifier = Modifier,
    placeholderCND :String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions()
) {
    OutlinedTextField(
        keyboardOptions = keyboardOptions,
        value = textFieldState.value,
        onValueChange = { it -> textFieldState.value = it },
        modifier = modifier
            .fillMaxWidth(),
        placeholder = {
            Row {
                Text(
                    text = label,
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier
                    .weight(1F))
                Text(
                    text = placeholderCND.orEmpty(),
                    style = MaterialTheme.typography.body1
                )
            }

        },
        colors = TextFieldDefaults
            .textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                focusedIndicatorColor = MaterialTheme.colors.surface,
                unfocusedIndicatorColor = MaterialTheme.colors.surface,
                placeholderColor = MaterialTheme.colors.onSurface
            ),
        shape = MaterialTheme.shapes.medium,
        textStyle = MaterialTheme
            .typography
            .body1
            .copy(textAlign = TextAlign.Start)
    )

}