package com.example.foodpart.ui.screens.fooddetails


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodpart.R
import com.example.foodpart.core.AppScreens
import com.example.foodpart.fooddata.foodList
import com.example.foodpart.ui.components.FoodDifficultyChip
import com.example.foodpart.ui.components.foodItem
import com.example.foodpart.ui.components.moreFoodItem

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FoodDetailsScreen(
    navController: NavController,
    id: Int
) {
    val food = foodList.find { it.id == id }!!
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val isFullImage = remember {
        mutableStateOf(false)
    }



    if (isFullImage.value) FullScreenPicture(
        isFullImage = isFullImage,
        imageRes = R.drawable.food_image_details
    )
    else
        ModalBottomSheetLayout(
            sheetState = bottomSheetState,
            sheetContent = {
                ReportModalBottomSheet(bottomSheetState = bottomSheetState)
            }) {
            Scaffold(
                topBar = {
                    FoodDetailsAppBar(navController, bottomSheetState)
                }
            ) { paddingValues ->
                LazyColumn(
                    modifier = Modifier
                        .padding(paddingValues),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    item {
                        Image(
                            painter = painterResource(id = R.drawable.food_image_details),
                            contentDescription = "Food Photo",
                            modifier = Modifier
                                .clip(shape = MaterialTheme.shapes.large)
                                .fillMaxWidth()
                                .height(250.dp)
                                .clickable {
                                    isFullImage.value = true
                                },

                            contentScale = ContentScale.FillWidth

                        )
                    }

                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = food.foodName,
                                style = MaterialTheme.typography.h1
                            )

                            Spacer(
                                modifier = Modifier
                                    .weight(1F)
                            )

                            Text(
                                modifier = Modifier.padding(8.dp, 0.dp),
                                text = "برای ۴ نفر",
                                style = MaterialTheme.typography.subtitle1
                            )

                            CookingTimeChip(time = food.cookingTime) {
                                navController.navigate(
                                    AppScreens.FoodList.createRoute(
                                        food.category.category,
                                        "زیر ${food.cookingTime}",
                                        null
                                    )
                                )
                            }
                        }
                    }

                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            food.meals.forEach { item ->
                                Chip(onClick = {
                                    navController.navigate(
                                        AppScreens.FoodList.createRoute(
                                            category = food.category.category,
                                            appBar = item,
                                            description = null
                                        )
                                    )
                                }) {
                                    Text(
                                        text = item,
                                        style = MaterialTheme.typography.subtitle2
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.weight(1F))

                            FoodDifficultyChip(food = food) {
                                navController.navigate(
                                    AppScreens.FoodList.createRoute(
                                        food.category.category,
                                        food.difficulty.difficulty,
                                        null
                                    )
                                )
                            }
                        }
                    }
                    item {
                        foodDetailsTab(food)
                    }
                    item {
                        Text(
                            text = "بیشتر از این دسته",
                            style = MaterialTheme.typography.h3
                        )
                    }
                    item {
                        LazyRow(
                            contentPadding = PaddingValues(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            items(items = foodList
                                .filter { it.category == food.category }
                                .filter { foodList.indexOf(it) <= 5 }) { item ->
                                foodItem(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .clickable {
                                            navController.navigate(
                                                AppScreens.FoodDetails.createRoute(
                                                    item.id
                                                )
                                            )
                                        },
                                    name = item.foodName,
                                    time = item.cookingTime
                                )

                            }
                            item {
                                moreFoodItem(
                                    modifier = Modifier
                                        .clickable {
                                            navController.navigate(
                                                AppScreens.FoodList.createRoute(
                                                    food.category.name,
                                                    food.category.name,
                                                    null
                                                )
                                            )
                                        }
                                )
                            }

                        }
                    }
                }
            }

        }

}