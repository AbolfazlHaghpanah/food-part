package com.example.foodpart.ui.screens.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodpart.core.AppScreens
import com.example.foodpart.ui.components.foodItem

@Composable
fun FoodListByCategory(
    viewModel: CategoryScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavController
) {
    val indicationState = remember { MutableInteractionSource() }

    val foodListState by viewModel.foodListByCategoryFlow.collectAsState()
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),

        ) {
        items(foodListState) { item ->

            foodItem(
                modifier = Modifier
                    .clickable(
                        interactionSource = indicationState,
                        indication = null
                    ) {
                        navController.navigate(AppScreens.FoodDetails.createRoute(item.id))
                    },
                item.foodName,
                item.cookingTime
            )
        }
    }
}