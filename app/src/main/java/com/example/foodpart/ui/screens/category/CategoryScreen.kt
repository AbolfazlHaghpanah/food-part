package com.example.foodpart.ui.screens.category

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodpart.R
import com.example.foodpart.core.AppScreens
import com.example.foodpart.fooddata.foodList
import com.example.foodpart.ui.components.foodItem


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun categoryScreen(
    viewModel: CategoryScreenViewModel,
    navController: NavController
) {
    val categorySelectedState by viewModel.categoryFlow.collectAsState()
    val subCategoryState by viewModel.subCategoryFlow.collectAsState()
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name_persian),
                        style = MaterialTheme.typography.h1
                    )
                },
                backgroundColor = MaterialTheme.colors.background
            )
        }
    ) {
        Column(
            Modifier.padding(it)
        ) {
            //category
            LazyRow(
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(foodList.map { it.category }.toSet().toList()) { item ->
                    categoryItem(
                        category = item,
                        isSelected = categorySelectedState == item,
                        modifier = Modifier
                            .clickable { viewModel.setCategory(item) }
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colors.onBackground)
            )
            //subCategory
            LazyRow(
                contentPadding = PaddingValues(16.dp, 2.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(foodList.map { it.subCategory }.toSet().toList()) { item ->
                    Chip(
                        onClick = {
                            viewModel.setSubCategory(item)
                        },
                        border = if (subCategoryState == item) BorderStroke(
                            color = MaterialTheme.colors.primary,
                            width = 1.dp
                        ) else null
                    ) {
                        Text(
                            text = item,
                            style = MaterialTheme.typography.subtitle1,
                            color = if (subCategoryState == item) MaterialTheme.colors.primary
                            else MaterialTheme.colors.onBackground
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
            //foodList
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth(),
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),

                ) {
                items(foodList.filter { it.category == categorySelectedState && it.subCategory == subCategoryState }) { item ->

                    foodItem(
                        modifier = Modifier
                            .clickable {
                                navController.navigate(AppScreens.FoodDetails.createRoute(item.id))
                            },
                        item.foodName,
                        item.cookingTime
                    )
                }
            }


        }
    }

}
