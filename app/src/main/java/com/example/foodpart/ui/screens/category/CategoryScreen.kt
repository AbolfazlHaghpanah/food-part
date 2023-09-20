package com.example.foodpart.ui.screens.category

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodpart.R
import com.example.foodpart.core.FoodPartBottomNavigation


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CategoryScreen(
    navController: NavController
) {
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
        Column(
            Modifier.padding(it)
        ) {

            CategoriesList()


            Spacer(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colors.onBackground)
            )


            SubCategoriesList()

            FoodListByCategory(navController = navController)


        }
    }

}








