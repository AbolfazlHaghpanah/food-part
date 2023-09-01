package com.example.foodpart.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun moreFoodItem(
    modifier:Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(136.dp)
            .width(136.dp)
    ){
        Row(
            modifier = Modifier
                .width(136.dp)
                .height(84.dp)
                .background(
                    color = MaterialTheme.colors.secondary,
                    shape = MaterialTheme.shapes.large
                )
                .align(Alignment.TopCenter),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "بیشتر",
                style = MaterialTheme.typography.h3
            )
            Icon(imageVector = Icons.Rounded.KeyboardArrowLeft, contentDescription = "more")
        }

    }
}