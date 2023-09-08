package com.example.foodpart.ui.screens.whattocook

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodpart.core.AppScreens
import com.example.foodpart.core.FoodPartBottomNavigation
import com.example.foodpart.fooddata.Categories
import com.example.foodpart.ui.components.FoodPartTextField
import com.example.foodpart.ui.components.FoodPartButton

@SuppressLint("SuspiciousIndentation", "FlowOperatorInvokedInComposition")
@Composable
fun WhatToCookScreen(
    navController: NavController,
    viewModel: WhatToCookScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

) {

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

            WhatToCookHint()

            FoodPartTextField(
                value = itemTextState,
                placeholder = "چی تو خونه داری ؟",
                modifier = Modifier
                    .height(56.dp),
                onValueChange = {itemTextState = it}
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
                onValueChange = {timeTextState = it},
                modifier = Modifier.height(56.dp),
                placeholderCND = "دقیقه",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
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

            FoodPartButton(onClick = {
                viewModel.setItemText(itemTextState)
                viewModel.setTimeText(timeTextState)
                navController.navigate(
                    AppScreens.FoodList.createRoute(
                        Categories.MAIN.category,
                        "چی بپزم؟",
                        viewModel.getDescriptionText()
                    )
                )
            },
                text = "جستجو",
                enabled = {
                    itemTextState.isNotEmpty() && timeTextState.isNotEmpty()
                }
            )


        }

    }
}
