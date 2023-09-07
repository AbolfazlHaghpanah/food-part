package com.example.foodpart.ui.screens.fooddetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.foodpart.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CookingTimeChip(
    time : String,
    onClick : ()->Unit
) {
    Chip(
        onClick = {
            onClick()
        },
        colors = ChipDefaults.chipColors(
            backgroundColor = MaterialTheme.colors.error.copy(
                alpha = 0.1F
            )
        )
    ) {
        Image(
            painter = painterResource(id = R.drawable.time),
            contentDescription = "Cooking Time",
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = time,
            style = MaterialTheme.typography.caption
        )
    }
}