package com.example.foodpart.ui.screens.fooddetails


import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LinearProgressIndicator
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.foodpart.R
import com.example.foodpart.core.AppScreens
import com.example.foodpart.fooddata.foodList
import com.example.foodpart.ui.components.FoodItem
import com.example.foodpart.ui.components.FoodPartButton
import com.example.foodpart.ui.components.MoreFoodItem
import com.example.foodpart.ui.components.Result
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FoodDetailsScreen(
    navController: NavController,
    viewModel: FoodDetailsViewModel = hiltViewModel(),
) {
    val food by viewModel.food.collectAsState()
    val foodResult by viewModel.foodResult.collectAsState()
    val similarFood by viewModel.foodSuggestionList.collectAsState()


    val foodfake = foodList.find { it.id == 1 }!!
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
        imageRes = food?.data?.image
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
                                            category = foodfake.category.category,
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
                if (foodResult == Result.Success) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(paddingValues),
                    ) {
                        item {

                            AsyncImage(
                                model = food?.data?.image,
                                contentDescription = food?.data?.name,
                                error = painterResource(id = R.drawable.food_image_details),
                                modifier = Modifier
                                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                                    .clip(shape = MaterialTheme.shapes.large)
                                    .fillMaxWidth()
                                    .height(250.dp)
                                    .clickable {
                                        isFullImage.value = true
                                    },
                                filterQuality = FilterQuality.None,
                                contentScale = ContentScale.FillWidth
                            )
                        }

                        item {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp, 0.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = food?.data?.name ?: "",
                                    style = MaterialTheme.typography.h1
                                )

                                Spacer(
                                    modifier = Modifier
                                        .weight(1F)
                                )

                                Text(
                                    modifier = Modifier.padding(16.dp, 0.dp),
                                    text = food?.data?.count ?: "",
                                    style = MaterialTheme.typography.caption
                                )
                                if (((food?.data?.readyTime?:0) + (food?.data?.cookTime?:0))!= 0 ){

                                    CookingTimeChip(
                                        time ="${ ((food?.data?.readyTime?:0) + (food?.data?.cookTime?:0))} دقیقه "
                                    ) {
                                        navController.navigate(
                                            AppScreens.FoodList.createRoute(
                                                foodfake.category.category,
                                                "زیر ${foodfake.cookingTime}",
                                                null
                                            )
                                        )
                                    }
                                }
                            }
                        }

                        item {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp, 0.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                food?.additionalInfo?.meals?.forEach { item ->
                                    Chip(
                                        modifier = Modifier
                                            .padding(end = 8.dp)
                                            .width(80.dp),
                                        onClick = {
                                            navController.navigate(
                                                AppScreens.FoodList.createRoute(
                                                    category = foodfake.category.category,
                                                    appBar = item.name,
                                                    description = null
                                                )
                                            )
                                        }) {
                                        Text(
                                            text = item.name,
                                            style = MaterialTheme.typography.caption,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.weight(1F))

                                FoodDifficultyChip(onClick = {
                                    navController.navigate(
                                        AppScreens.FoodList.createRoute(
                                            foodfake.category.category,
                                            foodfake.difficulty.difficulty,
                                            null
                                        )
                                    )
                                })
                            }
                        }
                        item {
                            FoodDetailsTab()
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
                                contentPadding = PaddingValues(
                                    end = 16.dp,
                                    start = 16.dp,
                                    bottom = 24.dp
                                ),
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                items(similarFood?.data ?: emptyList()) { item ->
                                    FoodItem(
                                        modifier = Modifier
                                            .clickable {
                                                navController.navigate(
                                                    AppScreens.FoodDetails.createRoute(
                                                        item.id
                                                    )
                                                )
                                            },
                                        name = item.name,
                                        time = if (((item.readyTime ?: 0) + (item.cookTime
                                                ?: 0)) != 0
                                        ) "${((item.readyTime ?: 0) + (item.cookTime ?: 0))} دقیقه " else ""
                                    )

                                }
                                item {
                                    MoreFoodItem(
                                        modifier = Modifier
                                            .clickable {
                                                navController.navigate(
                                                    AppScreens.FoodList.createRoute(
                                                        foodfake.category.category,
                                                        foodfake.category.category,
                                                        null
                                                    )
                                                )
                                            }
                                    )
                                }

                            }
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        if (foodResult == Result.Loading) {
                            LinearProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.TopCenter),
                                backgroundColor = MaterialTheme.colors.background
                            )
                        } else {
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
                                        viewModel.getFood()
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

