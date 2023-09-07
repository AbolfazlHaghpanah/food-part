package com.example.foodpart.ui.screens.whattocook

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.foodpart.R

@Composable
fun DifficultyList(
    viewModel : WhatToCookScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val selectedDifficultyItems by viewModel.selectedDifficultyItems.collectAsState()
    selectedDifficultyItems.icon = R.drawable.check_circle_outline
    val interactionSource = remember {
        MutableInteractionSource()
    }
    Row(

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        defficultyItemsList.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        if (item != selectedDifficultyItems) {
                            viewModel.setSelectedDifficultyItems(item)
                        }
                    }
            ) {
                Image(
                    painter = if (selectedDifficultyItems == item) painterResource(id = selectedDifficultyItems.icon)
                    else painterResource(id = R.drawable.check_circle_outline_not_selected),
                    contentDescription = item.name
                )
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.subtitle1
                )
            }

        }
    }
}