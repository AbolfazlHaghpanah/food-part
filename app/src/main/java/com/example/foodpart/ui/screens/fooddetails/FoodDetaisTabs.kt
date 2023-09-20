package com.example.foodpart.ui.screens.fooddetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FoodDetailsTab(
    viewModel: FoodDetailsViewModel = hiltViewModel()
) {
    val food by viewModel.food.collectAsState()
    val tabList by remember {
        derivedStateOf {
            if (food?.data?.point != null) listOf(
                "مواد اولیه",
                "طرز تهیه",
                "اطلاعات بیشتر"
            ) else listOf("مواد اولیه", "طرز تهیه")
        }
    }
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .padding(16.dp, 0.dp)
    ) {
        TabRow(
            modifier = Modifier
                .fillMaxWidth(0.7f),
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color.Transparent,
            divider = {},
            contentColor = MaterialTheme.colors.onBackground,
            indicator = {
                tabList.forEachIndexed { index, s ->
                    Box(
                        modifier = Modifier
                            .tabIndicatorOffset(it[pagerState.currentPage])
                            .height(2.dp)
                            .background(color = MaterialTheme.colors.primary)
                            .wrapContentWidth()
                    )

                }


            }
        ) {
            tabList.forEachIndexed { index, s ->
                Tab(modifier = Modifier
                    .fillMaxWidth(),
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = MaterialTheme.colors.onBackground,
                    text = {
                        Text(
                            modifier = Modifier.wrapContentWidth(unbounded = true),
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
        Box(
            Modifier
                .padding(end = 1.dp, start = 1.dp, top = 24.dp, bottom = 0.dp)
                .clip(shape = MaterialTheme.shapes.large)
                .background(
                    color = MaterialTheme.colors.surface,
                    shape = MaterialTheme.shapes.medium
                )
                .heightIn(300.dp)
        ) {
            HorizontalPager(
                pageCount = tabList.size,
                state = pagerState,
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 20.dp, start = 10.dp, end = 5.dp)
                        .fillMaxWidth()
                        .heightIn(300.dp),
                    text = when (it) {
                        0 -> food?.data?.ingredients ?: "برای این غذا موردی پیدا نشد"
                        1 -> food?.data?.recipe ?: "برای این غذا موردی پیدا نشد"
                        2 -> food?.data?.point ?: "برای این غذا موردی پیدا نشد"
                        else -> ""
                    },
                    style = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Start)
                )
            }

        }
    }
}
