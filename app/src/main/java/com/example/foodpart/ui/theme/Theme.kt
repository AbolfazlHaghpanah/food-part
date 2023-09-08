package com.example.foodpart.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val darkColorScheme = darkColors(
    primary = primary,
    secondary = secondary,
    background = background,
    surface = surface,
    onBackground = onBackground,
    onSurface = onSurface,
    onError = red,
)



@Composable
fun FoodPartTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        typography = Typography,
        content = content,
        colors = darkColorScheme,
        shapes = Shapes
    )
}