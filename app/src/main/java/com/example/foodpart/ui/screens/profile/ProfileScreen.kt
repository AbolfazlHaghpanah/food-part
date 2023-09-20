package com.example.foodpart.ui.screens.profile

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodpart.R
import com.example.foodpart.core.AppScreens
import com.example.foodpart.core.FoodPartBottomNavigation
import com.example.foodpart.ui.components.FoodPartButton

@Composable
fun ProfileScreen(
    navController: NavController
) {
    Scaffold(
        bottomBar = {
            FoodPartBottomNavigation(navController = navController)
        },
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp
            ) {
                Text(
                    text = "حساب کاربری",
                    modifier = Modifier.padding(start = 16.dp),
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Right,
                )
            }
        },
    ) {
        BackHandler {
            navController.popBackStack(route = AppScreens.Category.route, inclusive = false)
        }
        Column(
            modifier = Modifier
                .padding(it)
                .padding(start = 24.dp, end = 24.dp,top = 40.dp)
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                Modifier
                    .padding(bottom = 17.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .width(64.dp)
                        .height(64.dp)
                        .clip(RoundedCornerShape(59.dp)),
                    painter = painterResource(R.drawable.profile_photo),
                    contentDescription = "profile photo",
                    alignment = Alignment.CenterStart
                )

                Text(
                    text = "مهمان",
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body2,
                )
            }


            FoodPartButton(
                onClick = { navController.navigate(AppScreens.Login.route) },
                text = "وارد شوید"
            )

        }
    }

}