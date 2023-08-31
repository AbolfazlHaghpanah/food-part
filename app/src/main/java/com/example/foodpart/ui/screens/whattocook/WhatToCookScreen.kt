package com.example.foodpart.ui.screens.whattocook

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun whatToCookScreen(
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "چی بپزم ؟",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}