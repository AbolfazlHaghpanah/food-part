package com.example.foodpart.ui.screens.category

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.foodpart.R
import com.example.foodpart.ui.components.FoodPartBottomNavigation
import com.example.foodpart.ui.components.FoodPartButton
import com.example.foodpart.core.Result


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CategoryScreen(
    navController: NavController,
    viewModel: CategoryScreenViewModel = hiltViewModel()
) {

    val categoryResult by viewModel.categoryResult.collectAsState()
    val foodListResult by viewModel.foodListResult.collectAsState()


    Scaffold(
        bottomBar = {
            FoodPartBottomNavigation(navController = navController)
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name_persian),
                        style = MaterialTheme.typography.h1
                    )
                },
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp
            )
        }
    ) {

        if (categoryResult != Result.Success) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                if (categoryResult == Result.Loading) {
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
                                viewModel.getCategory()
                            },
                            text = "تلاش مجدد"
                        )
                    }
                }
            }
        } else {
            Column(
                Modifier.padding(it)
            ) {
                if (categoryResult == Result.Loading) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth(),
                        backgroundColor = MaterialTheme.colors.background
                    )
                }

                CategoriesList()

                if (categoryResult == Result.Success) Spacer(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(MaterialTheme.colors.onBackground)
                )

                SubCategoriesList()

                if (foodListResult == Result.Loading) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .fillMaxWidth(),
                        backgroundColor = MaterialTheme.colors.background
                    )
                }

                FoodListByCategory(navController = navController)

            }
        }


    }
}









