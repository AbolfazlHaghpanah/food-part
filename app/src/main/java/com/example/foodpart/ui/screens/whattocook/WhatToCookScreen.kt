package com.example.foodpart.ui.screens.whattocook

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodpart.core.AppScreens
import com.example.foodpart.core.FoodPartBottomNavigation
import com.example.foodpart.ui.components.FoodPartButton
import com.example.foodpart.ui.components.FoodPartTextField
import com.example.foodpart.ui.screens.foodlist.FoodListRequestType

@SuppressLint("SuspiciousIndentation", "FlowOperatorInvokedInComposition")
@Composable
fun WhatToCookScreen(
    navController: NavController,
    viewModel: WhatToCookScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

) {
    val isHintShow by viewModel.isHintShow.collectAsState()
    val difficulty by viewModel.selectedDifficultyItems.collectAsState()
    var isItemTextValid by remember {
        mutableStateOf(true)
    }
    var isTimeTextValid by remember {
        mutableStateOf(true)
    }
    val textFieldFocusManger = LocalFocusManager.current

    var itemTextState by remember {
        mutableStateOf("")
    }

    var timeTextState by remember {
        mutableStateOf("")
    }
    Scaffold(
        bottomBar = {
            FoodPartBottomNavigation(navController = navController)
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "چی بپزم؟",
                        style = MaterialTheme.typography.h2
                    )
                },
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ) {

            if (isHintShow)
                WhatToCookHint()


            FoodPartTextField(
                value = itemTextState,
                placeholder = "چی تو خونه داری ؟",
                modifier = Modifier
                    .height(56.dp),
                onValueChange = {
                    itemTextState = it
                    isItemTextValid = true
                },
                isError = !isItemTextValid,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {
                    textFieldFocusManger.moveFocus(FocusDirection.Down)
                }),
                errorMassage = if (itemTextState.isEmpty())"این فیلد باید پر بشه!" else "حداقل ۳ حرف داشته باشه"
            )


            Text(
                text = "با ، جدا کنید ",
                style = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Start),
                modifier = Modifier
                    .fillMaxWidth()
            )

            FoodPartTextField(
                value = timeTextState,
                placeholder = "چقد وقت داری؟",
                onValueChange = {
                    timeTextState = it
                    isTimeTextValid = true
                },
                modifier = Modifier.height(56.dp),
                placeholderCND = "دقیقه",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                isError = !isTimeTextValid,
                keyboardActions = KeyboardActions(),
                errorMassage = "این فیلد باید پر بشه!"
            )

            Text(
                text = "دستور چقدر سخت باشه ؟",
                style = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Start),
                modifier = Modifier
                    .fillMaxWidth()
            )

            DifficultyList()

            Spacer(
                modifier = Modifier
                    .weight(1F)
            )

            FoodPartButton(
                onClick = {
                    textFieldFocusManger.clearFocus()

                    when ("") {
                        itemTextState -> isItemTextValid = false
                        timeTextState -> isTimeTextValid = false
                        else -> {
                            if(itemTextState.length>2){
                                viewModel.setItemText(itemTextState)
                                viewModel.setTimeText(timeTextState)
                                FoodListRequestType.WhatToCook.createWhatToCookItems(
                                    ingredient = itemTextState,
                                    difficulties = when (difficulty) {
                                        DifficultyItems.Easy -> 1
                                        DifficultyItems.Medium -> 2
                                        DifficultyItems.Hard -> 3
                                        DifficultyItems.NoMatter -> null
                                    },
                                    timeLimit = timeTextState.toInt(),
                                    description = viewModel.getDescriptionText()
                                )
                                navController.navigate(
                                    AppScreens.FoodList.createRoute(
                                        "",
                                        "چی بپزم؟",
                                        requestType = FoodListRequestType.WhatToCook.type
                                    )
                                )
                            }else{
                                isItemTextValid = false
                            }

                        }
                    }
                },
                text = "جستجو"
            )


        }

    }
}
