package com.example.foodpart.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FoodPartTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    placeholderCND: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions(),
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    errorMassage: String? = null
) {

    Column {
        val bringIntoViewRequester = remember {
            BringIntoViewRequester()
        }
        val scope = rememberCoroutineScope()
        OutlinedTextField(

            value = value,
            onValueChange = onValueChange,
            modifier = modifier
                .fillMaxWidth()
                .bringIntoViewRequester(bringIntoViewRequester)
                .onFocusEvent {
                    if (it.isFocused) {
                        scope.launch {
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                },
            placeholder = {
                Row {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.body1
                    )
                    Spacer(
                        modifier = Modifier
                            .weight(1F)
                    )
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
                    placeholderColor = if (!isError) MaterialTheme.colors.onSurface
                    else MaterialTheme.colors.error
                ),
            shape = MaterialTheme.shapes.medium,
            textStyle = MaterialTheme
                .typography
                .body1
                .copy(textAlign = TextAlign.Start),
            visualTransformation = visualTransformation,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            isError = isError,
        )
        if (isError && errorMassage != null) {
            Text(
                text = errorMassage,
                style = MaterialTheme.typography.caption.copy(textAlign = TextAlign.Start),
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(top = 4.dp, start = 4.dp)
            )
        }
    }


}