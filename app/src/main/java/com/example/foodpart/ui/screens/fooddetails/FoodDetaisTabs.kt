package com.example.foodpart.ui.screens.fooddetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.foodpart.fooddata.FoodData
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FoodDetailsTab(
    food: FoodData
) {
    val tabList = listOf("مواد اولیه", "طرز تهیه", "اطلاعات بیشتر")
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    Column {
        TabRow(
            modifier = Modifier
                .fillMaxWidth(0.7f),
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.onBackground,
            indicator = {

                tabList.forEachIndexed { index, s ->
                    if (index != pagerState.currentPage) {
                        Box(
                            modifier = Modifier
                                .tabIndicatorOffset(it[index])
                                .height(1.dp)
                                .background(color = MaterialTheme.colors.background)
                                .width(20.dp)
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .tabIndicatorOffset(it[index])
                                .height(1.dp)
                                .background(color = MaterialTheme.colors.primary)
                                .width(10.dp)
                                .clip(shape = MaterialTheme.shapes.medium),
                        )
                    }
                }


            }
        ) {
            tabList.forEachIndexed { index, s ->
                Tab(modifier = Modifier
                    .wrapContentWidth(unbounded = true)
                    .clip(shape = MaterialTheme.shapes.small),

                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = MaterialTheme.colors.onBackground,
                    text = {
                        Text(
                            text = s,
                            style = MaterialTheme.typography.h3
                        )
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }

        HorizontalPager(
            pageCount = 3,
            state = pagerState,
        ) {
            Box(
                Modifier
                    .padding(8.dp, 16.dp)
                    .clip(shape = MaterialTheme.shapes.large)
                    .background(
                        color = MaterialTheme.colors.surface,
                        shape = MaterialTheme.shapes.medium
                    )
                    .height(400.dp)
            ) {
                when (it) {
                    0 -> {

                        Text(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            text = food.recipes,
                            style = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Start)
                        )
                    }

                    1 -> {

                        Text(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            text = food.recipes,
                            style = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Start)
                        )
                    }

                    2 -> {

                        Text(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            text = food.recipes,
                            style = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Start)
                        )
                    }
                }
            }


        }
    }
}
