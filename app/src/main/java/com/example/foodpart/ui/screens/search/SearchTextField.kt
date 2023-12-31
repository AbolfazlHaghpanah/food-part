package com.example.foodpart.ui.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel,
) {
    val text by viewModel.text.collectAsState()
    val isError by viewModel.isError.collectAsState()
    var textState: String by remember {
        mutableStateOf("")
    }

    Column (
        modifier = modifier
    ){
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = textState,
            onValueChange = {
                textState = it
            },
            textStyle = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Start),
            placeholder = {
                Text(
                    text = "اینجا بنویس ...",
                    style = MaterialTheme.typography.body1
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
                if (text != "" || textState != "")
                    IconButton(onClick = {
                        textState = ""
                        viewModel.setText("")
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.Clear,
                            contentDescription = "Clear"
                        )
                    }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (textState.length>=3){
                        viewModel.setText(textState)
                        viewModel.getFoodListBySearch()
                    }else{
                        viewModel.setText("")
                    }

                }
            )
        )
        if ((textState.length < 3) && (textState.isNotEmpty())){
            Text(
                text = "حداقل ۳ کاراکتر وارد کنید",
                style = MaterialTheme.typography.caption.copy(textAlign = TextAlign.Start),
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.padding(top = 4.dp, start = 4.dp)
            )
        }

    }



}