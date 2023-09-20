package com.example.foodpart.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
fun FoodPartButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: () -> Boolean = { true },
    isLoading: Boolean = false
) {
    Button(
        enabled = enabled(),
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = MaterialTheme.shapes.medium
    ) {

        if (isLoading) {
            CircularProgressIndicator(
                strokeCap = StrokeCap.Round,
                modifier = Modifier.size(24.dp),
                strokeWidth = 3.dp,
                backgroundColor = MaterialTheme.colors.primary,
                color = MaterialTheme.colors.onBackground
            )
        } else {
            Text(
                text = text,
                style = MaterialTheme.typography.button,
                color = MaterialTheme.colors.onBackground
            )

        }
    }
}