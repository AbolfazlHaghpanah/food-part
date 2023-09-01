package com.example.foodpart.ui.screens.fooddetails


import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodpart.R
import com.example.foodpart.core.AppScreens
import com.example.foodpart.fooddata.foodList
import com.example.foodpart.ui.components.foodDifficultyChip
import com.example.foodpart.ui.components.foodPartButton
import com.example.foodpart.ui.components.foodPartTextField
import com.example.foodpart.ui.components.moreFoodItem
import com.example.foodpart.ui.screens.foodlist.foodItem
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun foodDetailsScreen(
    navController: NavController,
    id: Int
) {
    val food = foodList.filter { it.id == id }[0]
    var reportTextState = remember {
        mutableStateOf("")
    }
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val isFullImage = remember {
        mutableStateOf(false)
    }



    if (isFullImage.value) fullScreenPicture(
        isFullImage = isFullImage,
        imageRes = R.drawable.food_image_details
    )


    else
        ModalBottomSheetLayout(
            sheetState = bottomSheetState,
            sheetContent = {
                Column(
                    modifier = Modifier
                        .background(color = MaterialTheme.colors.background)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "گزارش دستور به عنوان نامناسب ",
                        style = MaterialTheme.typography.h3
                    )

                    foodPartTextField(
                        modifier = Modifier
                            .padding(top = 16.dp),
                        textFieldState = reportTextState,
                        label = "اینجا بنویسید "
                    )

                    foodPartButton(
                        modifier = Modifier
                            .padding(top = 16.dp),
                        onClick = {
                            scope.launch {
                                bottomSheetState.hide()
                            }
                        },
                        text = "ثبت"
                    )
                }


            }) {
            Scaffold(

                topBar = {
                    foodDetailsAppBar(navController, bottomSheetState)
                }
            ) {

                LazyColumn(
                    modifier = Modifier
                        .padding(it),
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

                            Chip(
                                onClick = { /*TODO*/ },
                                colors = ChipDefaults.chipColors(
                                    backgroundColor = MaterialTheme.colors.error.copy(
                                        alpha = 0.1F
                                    )
                                )
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.time),
                                    contentDescription = "Cooking Time",
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = food.cookingTime,
                                    style = MaterialTheme.typography.subtitle1
                                )
                            }
                        }
                    }

                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            food.meals.forEach { item ->
                                Chip(onClick = {}) {
                                    Text(
                                        text = item,
                                        style = MaterialTheme.typography.subtitle2
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.weight(1F))

                            foodDifficultyChip(food = food) {
                                navController.navigate(
                                    AppScreens.FoodList.createRoute(
                                        food.category,
                                        food.difficulty
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
                            items(foodList
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
                                                    food.category,
                                                    food.category
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