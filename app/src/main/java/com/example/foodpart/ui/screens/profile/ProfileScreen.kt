package com.example.foodpart.ui.screens.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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
                backgroundColor = MaterialTheme.colors.background
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
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 24.dp, end = 24.dp)
                    .background(color = MaterialTheme.colors.background),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .padding(1.dp)
                            .width(64.dp)
                            .height(64.dp)
                            .clip(RoundedCornerShape(59.dp)),
                        painter = painterResource(R.drawable.profile_photo),
                        contentDescription = "profile photo",
                        alignment = Alignment.CenterStart
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "مهمان",
                        modifier = Modifier
                            .width(35.dp)
                            .height(20.dp),
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(17.dp))

                Button(
                    onClick = {
                        navController.navigate(AppScreens.Login.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(327.dp)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primary),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = "وارد شوید",
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.button,
                        color = MaterialTheme.colors.onBackground,
                    )
                }

                Spacer(modifier = Modifier.height(530.dp))

            }
        }
    )
}