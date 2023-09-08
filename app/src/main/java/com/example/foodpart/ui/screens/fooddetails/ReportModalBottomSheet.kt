package com.example.foodpart.ui.screens.fooddetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.foodpart.ui.components.FoodPartButton
import com.example.foodpart.ui.components.FoodPartTextField
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ReportModalBottomSheet(
    bottomSheetState: ModalBottomSheetState
) {
    val focusManger = LocalFocusManager.current
    var reportTextState by remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colors.secondary)
            .padding(16.dp)
    ) {
        Text(
            text = "گزارش دستور به عنوان نامناسب ",
            style = MaterialTheme.typography.h3
        )

        FoodPartTextField(
            modifier = Modifier
                .padding(top = 16.dp)
                .height(84.dp),
            value = reportTextState,
            onValueChange = {reportTextState = it},
            placeholder = "اینجا بنویسید "
        )

        FoodPartButton(
            modifier = Modifier
                .padding(top = 16.dp),
            onClick = {
                scope.launch {
                    focusManger.clearFocus()
                    bottomSheetState.hide()
                }
            },
            text = "ثبت"
        )
    }
}