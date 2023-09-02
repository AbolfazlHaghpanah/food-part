package com.example.foodpart.ui.screens.foodlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodpart.core.AppScreens
import com.example.foodpart.fooddata.foodList
import com.example.foodpart.ui.components.foodItem

@Composable
fun foodListScreen(
    navController: NavController,
    category: String,
    appBarText: String,

    ) {
    Scaffold(
        topBar = {
            foodListAppBar(
                navController = navController,
                appBarText = appBarText
            )
        }
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth(),
            columns = GridCells
                .Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(foodList.filter { it.category == category }) { item ->
                foodItem(
                    modifier = Modifier
                        .clickable {
                            navController
                                .navigate(
                                    AppScreens
                                        .FoodDetails
                                        .createRoute(item.id)
                                )
                        },
                    item.foodName,
                    item.cookingTime
                )
            }
        }
    }
}
