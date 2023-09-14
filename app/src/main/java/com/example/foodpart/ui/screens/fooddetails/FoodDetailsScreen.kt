package com.example.foodpart.ui.screens.fooddetails


import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.foodpart.ui.components.FoodItem
import com.example.foodpart.ui.components.MoreFoodItem
import kotlinx.coroutines.launch

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
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    BackHandler {
        if (bottomSheetState.isVisible)
            scope.launch {
                bottomSheetState.hide()
            }
        else
            navController.popBackStack(
                route = AppScreens.Category.route,
                inclusive = false
            )
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
                scaffoldState = scaffoldState,
                snackbarHost = {
                    SnackbarHost(it) {
                        Snackbar(
                            modifier = Modifier
                                .padding(bottom = 85.dp, start = 8.dp, end = 8.dp),
                            contentColor = MaterialTheme.colors.onBackground,
                            backgroundColor = MaterialTheme.colors.secondary,
                            action = {
                                TextButton(onClick = {
                                    navController.navigate(
                                        AppScreens.FoodList.createRoute(
                                            category = food.category.category,
                                            appBar = "علاقه مندی ها",
                                            description = null
                                        )
                                    )
                                }) {
                                    Text(
                                        text = "علاقه مندی ها",
                                        style = MaterialTheme.typography.caption,
                                        color = MaterialTheme.colors.primary
                                    )
                                }
                            },

                            ) {
                            Text(
                                text = "دستور به علاقه مندی ها اضافه شد",
                                style = MaterialTheme.typography.caption
                            )
                        }
                    }
                },
                topBar = {
                    FoodDetailsAppBar(navController, bottomSheetState, scaffoldState)
                }
            ) { paddingValues ->
                LazyColumn(
                    modifier = Modifier
                        .padding(paddingValues),
                ) {
                    item {
                        Image(
                            painter = painterResource(id = R.drawable.food_image_details),
                            contentDescription = "Food Photo",
                            modifier = Modifier
                                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
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
                            modifier = Modifier
                                .padding(16.dp,0.dp),
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
                                modifier = Modifier.padding(16.dp, 0.dp),
                                text = "برای ۴ نفر",
                                style = MaterialTheme.typography.caption
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
                            modifier = Modifier
                                .padding(16.dp,0.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            food.meals.forEach { item ->
                                Chip(
                                    modifier = Modifier
                                        .padding(end = 8.dp)
                                        .width(80.dp),
                                    onClick = {
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
                                        style = MaterialTheme.typography.caption,
                                        modifier = Modifier
                                            .fillMaxWidth()
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
                        FoodDetailsTab(food)
                    }
                    item {
                        Text(
                            modifier = Modifier
                                .padding(top = 11.dp, start = 24.dp, bottom = 16.dp),
                            text = "بیشتر از این دسته",
                            style = MaterialTheme.typography.h3
                        )
                    }
                    item {
                        LazyRow(
                            contentPadding = PaddingValues(end = 16.dp,start = 16.dp, bottom = 24.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            items(items = foodList
                                .filter { it.category.category == food.category.category }
                                .filter { foodList.indexOf(it) <= 5 }) { item ->
                                FoodItem(
                                    modifier = Modifier
                                        .clickable {
                                            navController.navigate(
                                                AppScreens.FoodDetails.createRoute(
                                                    item.id.toString()
                                                )
                                            )
                                        },
                                    name = item.foodName,
                                    time = item.cookingTime
                                )

                            }
                            item {
                                MoreFoodItem(
                                    modifier = Modifier
                                        .clickable {
                                            navController.navigate(
                                                AppScreens.FoodList.createRoute(
                                                    food.category.category,
                                                    food.category.category,
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