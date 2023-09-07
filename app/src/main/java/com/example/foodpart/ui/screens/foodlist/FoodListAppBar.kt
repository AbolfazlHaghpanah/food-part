package com.example.foodpart.ui.screens.foodlist

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun FoodListAppBar(
    navController: NavController,
    appBarText: String
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    ) {
        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Icon(
                imageVector = Icons.Rounded.KeyboardArrowRight,
                contentDescription = "Back",
                tint = MaterialTheme.colors.onBackground
            )
        }
        Text(
            text = appBarText,
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onBackground
        )
    }
}