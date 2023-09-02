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
import com.example.foodpart.fooddata.FoodData
import com.example.foodpart.ui.theme.green
import com.example.foodpart.ui.theme.red
import com.example.foodpart.ui.theme.yellow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun foodDifficultyChip(
    food : FoodData,
    onClick : ()->Unit
) {
    Chip(
        onClick = onClick,
        border = BorderStroke(
            1.dp, color = when (food.difficulty) {
                "آسان" -> green
                "متوسط" -> yellow
                "پیچیده" -> red
                else -> yellow
            }
        ),
        colors = ChipDefaults.chipColors(
            backgroundColor = when (food.difficulty) {
                "آسان" -> green.copy(alpha = (0.1f))
                "متوسط" -> yellow.copy(alpha = (0.1f))
                "پیچیده" -> red.copy(alpha = (0.1f))
                else -> yellow.copy(alpha = (0.1f))
            }
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.deficulity),
            contentDescription = "",
            tint = when (food.difficulty) {
                "آسان" -> green
                "متوسط" -> yellow
                "پیچیده" -> red
                else -> yellow
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = food.difficulty,
            style = MaterialTheme.typography.subtitle1
        )
    }
}