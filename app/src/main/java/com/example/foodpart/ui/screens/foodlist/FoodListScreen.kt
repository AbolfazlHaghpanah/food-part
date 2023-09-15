package com.example.foodpart.ui.screens.foodlist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.foodpart.core.AppScreens
import com.example.foodpart.fooddata.foodList
import com.example.foodpart.ui.components.FoodItem
import kotlinx.coroutines.launch

@Composable
fun FoodListScreen(
    navController: NavController,
    viewModel: FoodListViewModel = hiltViewModel()
    ) {
    val appBarText = viewModel.appBarText
    val foodList by viewModel.foodList.collectAsState()
    val lazyColumnState = rememberLazyGridState()
    val scope = rememberCoroutineScope()
    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {

            AnimatedVisibility(
                visible = lazyColumnState.firstVisibleItemIndex != 0,
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

                {
                    item(
                        span = {
                            GridItemSpan(2)
                        }
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = "description",
                            style = MaterialTheme.typography.subtitle1.copy(textAlign = TextAlign.Start)
                        )
                    }
                }

                items(foodList) { item ->
                    FoodItem(
                        modifier = Modifier
                            .clickable {
                                navController
                                    .navigate(
                                        AppScreens
                                            .FoodDetails
                                            .createRoute(item.id.toString())
                                    )
                            },
                        name = item.name,
                        time = if (((item.readyTime ?: 0) + (item.cookTime
                                ?: 0)) != 0
                        ) "${((item.readyTime ?: 0) + (item.cookTime ?: 0))} دقیقه " else "",
                    )
                }
            }
        }
    }
}
