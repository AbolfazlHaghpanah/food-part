package com.example.foodpart.ui.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.navigation.NavController
import com.example.foodpart.core.AppScreens
import com.example.foodpart.fooddata.foodList
import com.example.foodpart.ui.screens.foodlist.foodItem

@Composable
fun searchScreen(
    navController: NavController,
    viewModel: SearchViewModel
) {
    val text by viewModel.text.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "دنبال چی میگردی ؟",
                        style = MaterialTheme.typography.h1
                    )
                },
                backgroundColor = MaterialTheme.colors.background
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {
            searchTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                viewModel = viewModel
            )


            if (text.length != 0) {
                Text(
                    text = "نتایج جستجو با $text",
                    style = MaterialTheme.typography.subtitle1
                        .copy(textAlign = TextAlign.Start),
                    modifier = Modifier
                        .fillMaxWidth()
                )

                if (foodList.filter { it.foodName.contains(text) }.size != 0) {
                    viewModel.setError(false)

                    LazyVerticalGrid(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        columns = GridCells.Fixed(2),
                    ) {


                        items(foodList.filter { it.foodName.contains(text) }) { item ->
                            foodItem(
                                modifier = Modifier
                                    .clickable {
                                        navController
                                            .navigate(
                                                AppScreens
                                                    .FoodDetails
                                                    .createRoute(item.id)
                                            )
                                    }, name = item.foodName,
                                time = item.cookingTime
                            )

                        }
                    }
                } else {
                    viewModel.setError(true)

                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "اوپس چیزی پیدا نشد",
                            style = MaterialTheme.typography.h3
                        )
                    }


                }

            } else viewModel.setError(false)
        }
    }
}