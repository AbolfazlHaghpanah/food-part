package com.example.foodpart.ui.screens.category

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodpart.fooddata.foodList
import com.example.foodpart.ui.theme.FoodPartTheme

@OptIn(ExperimentalMaterialApi::class)
@Preview(showSystemUi = true)
@Composable
fun categoryScreen() {
    val categorySelectedState = remember {
        mutableStateOf(false)
    }
    FoodPartTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "فود پارت",
                            style = MaterialTheme.typography.h1
                        )
                    },
                )
            }
        ) {
            Column(
                Modifier.padding(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                LazyRow(
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(foodList) { item ->
                        categoryItem(category = item.category, isSelected = false)
                    }
                }

                Spacer(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(MaterialTheme.colors.onBackground)
                )

                LazyRow(
                    contentPadding = PaddingValues(16.dp, 2.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(foodList) { item ->
                        Chip(onClick = { /*TODO*/ }) {
                            Text(
                                text = item.subCategory,
                                style = MaterialTheme.typography.subtitle1
                            )
                        }

                    }
                }

                Spacer(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(MaterialTheme.colors.onBackground)
                )

                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth(),
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),

                    ) {
                    items(foodList) { item ->
                        foodItem(item.foodName,item.cookingTime)
                    }
                }


            }
        }

    }
}