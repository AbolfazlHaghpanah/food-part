package com.example.foodpart.ui.screens.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodpart.core.AppScreens
import com.example.foodpart.ui.components.FoodItem
import com.example.foodpart.ui.components.FoodPartButton
import com.example.foodpart.ui.components.Result

@Composable
fun FoodListByCategory(
    viewModel: CategoryScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavController
) {
    val indicationState = remember { MutableInteractionSource() }
    val foodList by viewModel.foodList.collectAsState()
    val foodListResult by viewModel.foodListResult.collectAsState()


    if (foodListResult == Result.Success) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth(),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(40.dp, 16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            items(foodList ?: emptyList()) { item ->
                FoodItem(
                    modifier = Modifier
                        .clickable(
                            interactionSource = indicationState,
                            indication = null
                        ) {
                            navController.navigate(AppScreens.FoodDetails.createRoute(item.id))
                        },
                    name = item.name,
                    time = if (((item.readyTime ?: 0) + (item.cookTime
                            ?: 0)) != 0
                    ) "${((item.readyTime ?: 0) + (item.cookTime ?: 0))} دقیقه " else "",
                    image = item.image
                )
            }
        }
    } else {
        if (foodListResult != Result.Loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center),
                    verticalArrangement = Arrangement.spacedBy(21.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "غذایی برای نمایش وجود ندارد",
                        style = MaterialTheme.typography.h3
                    )
                    FoodPartButton(
                        modifier = Modifier
                            .width(130.dp)
                            .height(45.dp),
                        onClick = {
                            viewModel.getFoodList()
                        },
                        text = "تلاش مجدد"
                    )
                }
            }
        }
    }
}