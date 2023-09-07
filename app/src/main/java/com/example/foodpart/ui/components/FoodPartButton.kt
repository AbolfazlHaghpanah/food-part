package com.example.foodpart.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FoodPartButton(
    onClick : ()->Unit,
    text : String,
    modifier: Modifier = Modifier,
    enabled : ()->Boolean ={true}
) {
    Button(
        enabled = enabled() ,
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.onBackground
        )
    }
}