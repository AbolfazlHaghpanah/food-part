package com.example.foodpart.ui.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.foodpart.core.AppScreens
import com.example.foodpart.ui.components.FoodPartBottomNavigation
import com.example.foodpart.ui.components.FoodItem
import com.example.foodpart.ui.components.FoodPartButton
import com.example.foodpart.core.Result

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val foodListResult by viewModel.result.collectAsState()
    val foodList by viewModel.foodList.collectAsState()
    val text by viewModel.text.collectAsState()
    Scaffold(
        bottomBar = {
            FoodPartBottomNavigation(navController = navController)
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "دنبال چی میگردی ؟",
                        style = MaterialTheme.typography.h2
                    )
                },
                backgroundColor = MaterialTheme.colors.background
            )
        }
    ) {

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {
            SearchTextField(
                modifier = Modifier
                    .padding(16.dp, 0.dp)
                    .fillMaxWidth(),
                viewModel = viewModel,
            )


            if (text.isNotEmpty()) {
                if (foodListResult == Result.Success) {
                    Text(
                        text = "نتایج جستجو با $text",
                        style = MaterialTheme.typography.subtitle1
                            .copy(textAlign = TextAlign.Start),
                        modifier = Modifier
                            .padding(16.dp, 0.dp)
                            .fillMaxWidth()
                    )

                    if (foodList.isNotEmpty()) {
                        viewModel.setError(false)
                        LazyVerticalGrid(
                            verticalArrangement = Arrangement.spacedBy(24.dp),
                            horizontalArrangement = Arrangement.spacedBy(24.dp),
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(
                                bottom = 40.dp,
                                start = 40.dp,
                                end = 40.dp
                            )
                        ) {
                            items(foodList) { item ->
                                FoodItem(
                                    modifier = Modifier
                                        .clickable {
                                            navController
                                                .navigate(
                                                    AppScreens
                                                        .FoodDetails
                                                        .createRoute(item.id)
                                                )
                                        }, name = item.name,
                                    time = if (((item.readyTime ?: 0) + (item.cookTime
                                            ?: 0)) != 0
                                    ) "${((item.readyTime ?: 0) + (item.cookTime ?: 0))} دقیقه " else "",
                                    image = item.image
                                )

                            }
                        }
                    } else {
                        viewModel.setError(true)
                        viewModel.emptyFoodList()
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = "اوپس چیزی پیدا نشد",
                                style = MaterialTheme.typography.body1
                            )
                        }


                    }
                } else {
                    if (foodListResult == Result.Loading) {
                        LinearProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth(),
                            backgroundColor = MaterialTheme.colors.background
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier
                                    .align(Alignment.Center),
                                verticalArrangement = Arrangement.spacedBy(21.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "خطا در برقراری ارتباط",
                                    style = MaterialTheme.typography.h3
                                )
                                FoodPartButton(
                                    modifier = Modifier
                                        .width(130.dp)
                                        .height(45.dp),
                                    onClick = {
                                        viewModel.getFoodListBySearch()
                                    },
                                    text = "تلاش مجدد"
                                )
                            }

                        }
                    }


                }
            }
        }
    }
}
