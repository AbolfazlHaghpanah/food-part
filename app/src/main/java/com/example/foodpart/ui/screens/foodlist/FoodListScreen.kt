package com.example.foodpart.ui.screens.foodlist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.foodpart.core.AppScreens
import com.example.foodpart.ui.components.FoodItem
import com.example.foodpart.ui.components.FoodPartButton
import com.example.foodpart.core.Result
import kotlinx.coroutines.launch

@Composable
fun FoodListScreen(
    navController: NavController,
    viewModel: FoodListViewModel = hiltViewModel()
) {
    val appBarText = viewModel.appBarText
    val foodList by viewModel.foodList.collectAsState()
    val foodListResult by viewModel.foodListResult.collectAsState()
    val lazyColumnState = rememberLazyGridState()
    val scope = rememberCoroutineScope()
    val indicationState = remember { MutableInteractionSource() }
    val isFABShow by remember {
        derivedStateOf { lazyColumnState.firstVisibleItemIndex > 1 }
    }




    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            AnimatedVisibility(
                visible = isFABShow,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                FloatingActionButton(
                    backgroundColor = MaterialTheme.colors.primary,
                    onClick = {
                        scope.launch {
                            lazyColumnState.animateScrollToItem(0)
                        }
                    }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = "To Top",
                        modifier = Modifier.rotate(90F),
                        tint = MaterialTheme.colors.onBackground,
                    )
                }


            }
        },
        topBar = {
            FoodListAppBar(
                navController = navController,
                appBarText = appBarText ?: ""
            )
        }
    ) {

        if (foodListResult == Result.Success || foodListResult == Result.Idle) {

            if (foodList.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center),
                        verticalArrangement = Arrangement.spacedBy(21.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "موردی پیدا نشد",
                            style = MaterialTheme.typography.h3
                        )
                        FoodPartButton(
                            modifier = Modifier
                                .width(100.dp)
                                .height(45.dp),
                            onClick = {
                                navController.popBackStack()
                            },
                            text = "بازگشت"
                        )
                    }
                }
            } else {
                Column(
                    Modifier
                        .padding(it)
                ) {


                    LazyVerticalGrid(
                        state = lazyColumnState,
                        modifier = Modifier
                            .fillMaxWidth(),
                        columns = GridCells
                            .Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(24.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp),
                        contentPadding = PaddingValues(40.dp, 0.dp, 40.dp, 16.dp)
                    ) {

                        if (!viewModel.description.value.isNullOrEmpty()) {
                            item(
                                span = {
                                    GridItemSpan(2)
                                }
                            ) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = viewModel.description.value ?: "",
                                    style = MaterialTheme.typography.subtitle1.copy(textAlign = TextAlign.Start)
                                )
                            }
                        }

                        items(foodList) { item ->
                            FoodItem(
                                modifier = Modifier
                                    .clickable(
                                        interactionSource = indicationState,
                                        indication = null
                                    ) {
                                        navController
                                            .navigate(
                                                AppScreens
                                                    .FoodDetails
                                                    .createRoute(item.id)
                                            )
                                    },
                                name = item.name,
                                time = if (((item.readyTime ?: 0) + (item.cookTime
                                        ?: 0)) != 0
                                ) "${((item.readyTime ?: 0) + (item.cookTime ?: 0))} دقیقه " else "",
                                image = item.image
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
                if (foodListResult == Result.Loading) {
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
                                viewModel.getFoodList()
                            },
                            text = "تلاش مجدد"
                        )
                    }
                }
            }
        }

    }
}
