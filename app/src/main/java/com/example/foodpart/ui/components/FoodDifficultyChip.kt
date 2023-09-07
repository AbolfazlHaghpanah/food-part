package com.example.foodpart.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.foodpart.R
import com.example.foodpart.fooddata.Difficulties
import com.example.foodpart.fooddata.FoodData
import com.example.foodpart.ui.theme.green
import com.example.foodpart.ui.theme.red
import com.example.foodpart.ui.theme.yellow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FoodDifficultyChip(
    food: FoodData,
    onClick: () -> Unit
) {
    Chip(
        onClick = onClick,
        border = BorderStroke(
            1.dp, color = when (food.difficulty) {
                Difficulties.EASY -> green
                Difficulties.MEDIUM -> yellow
                Difficulties.HARD -> red
            }
        ),
        colors = ChipDefaults.chipColors(
            backgroundColor = when (food.difficulty) {
                Difficulties.EASY -> green.copy(alpha = (0.1f))
                Difficulties.MEDIUM -> yellow.copy(alpha = (0.1f))
                Difficulties.HARD -> red.copy(alpha = (0.1f))
            }
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.deficulity),
            contentDescription = "",
            tint = when (food.difficulty) {
                Difficulties.EASY -> green
                Difficulties.MEDIUM -> yellow
                Difficulties.HARD -> red
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = food.difficulty.difficulty,
            style = MaterialTheme.typography.caption
        )
    }
}