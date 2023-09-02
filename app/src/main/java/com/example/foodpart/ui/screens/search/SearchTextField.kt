package com.example.foodpart.ui.screens.search

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun searchTextField(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel,
) {
    val text by viewModel.text.collectAsState()
    val isError by viewModel.isError.collectAsState()
    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = {
            viewModel.SetText(it)
        },
        textStyle = MaterialTheme.typography.subtitle1.copy(textAlign = TextAlign.Start),
        placeholder = {
            Text(
                text = "اینجا بنویس ...",
                style = MaterialTheme.typography.subtitle1
            )
        },
        colors = TextFieldDefaults
            .textFieldColors(
                unfocusedIndicatorColor = MaterialTheme.colors.surface,
                focusedIndicatorColor = MaterialTheme.colors.onBackground,
                errorIndicatorColor = MaterialTheme.colors.error,
                errorLabelColor = MaterialTheme.colors.error,
                trailingIconColor = MaterialTheme.colors.onBackground,
            ),
        shape = MaterialTheme.shapes.medium,
        isError = isError,
        trailingIcon = {
            if (text != "")
                IconButton(onClick = {
                    viewModel.SetText("")
                }) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        contentDescription = "Clear"
                    )
                }
            else null
        },
    )
}