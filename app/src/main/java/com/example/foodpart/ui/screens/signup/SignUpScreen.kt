package com.example.foodpart.ui.screens.signup

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodpart.R
import com.example.foodpart.core.AppScreens
import com.example.foodpart.ui.components.FoodPartTextField

@Composable
fun SignUpScreen(
    navController: NavController
) {
    BackHandler {
        navController.popBackStack(AppScreens.Profile.route, inclusive = false)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background
            )
            {
                IconButton(onClick = {
                    navController.popBackStack(AppScreens.Profile.route, inclusive = false)
                }) {
                    Icon(
                        modifier = Modifier
                            .padding(1.dp)
                            .width(24.dp)
                            .height(24.dp),
                        tint = MaterialTheme.colors.onBackground,
                        painter = rememberVectorPainter(Icons.Rounded.KeyboardArrowRight),
                        contentDescription = "Back"
                    )
                }
                Text(
                    text = "ثبت نام",
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Right,
                )
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp)
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(color = MaterialTheme.colors.background),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    Modifier
                        .width(75.dp)
                        .height(75.dp)
                        .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                        .background(
                            color = MaterialTheme.colors.primary,
                            shape = RoundedCornerShape(size = 59.dp)
                        ),
                    horizontalArrangement = Arrangement.spacedBy(
                        10.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(1.dp)
                            .width(55.dp)
                            .height(55.dp),
                        tint = Color.White,
                        painter = painterResource(R.drawable.logo_dark),
                        contentDescription = "Icon"
                    )
                }

                Spacer(modifier = Modifier.height(50.dp))

                Text(
                    text = "خوش آمدید",
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.h1
                        .copy(textAlign = TextAlign.Start)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "برای ثبت نام اطلاعات خود را وارد کنید",
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.body1
                        .copy(textAlign = TextAlign.Start)
                )

                Spacer(modifier = Modifier.height(43.dp))

                var username by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                var repeatPass by remember { mutableStateOf("") }

                FoodPartTextField(
                    value = username,
                    onValueChange = { username = it },
                    placeholder = "نام کاربری"
                )

                Spacer(modifier = Modifier.height(8.dp))

                FoodPartTextField(
                    value = password,
                    onValueChange = { password = it },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    placeholder = "رمز عبور"
                )

                Spacer(modifier = Modifier.height(8.dp))

                FoodPartTextField(
                    value = repeatPass,
                    onValueChange = { repeatPass = it },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    placeholder = "تکرار رمز عبور"
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    enabled = username.isNotEmpty()
                            && password.isNotEmpty()
                            && password == repeatPass,
                    onClick = {
                        navController.navigate(AppScreens.Profile.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(328.dp)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primary),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = "تایید",
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.button,
                        color = MaterialTheme.colors.onBackground,
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    Modifier
                        .padding(start = 205.dp)
                ) {
                    Text(
                        text = "قبلا ثبت نام کردید؟",
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.onBackground,
                    )

                    Text(
                        modifier = Modifier
                            .clickable {
                                navController.navigate(AppScreens.Login.route)
                            },
                        text = " ورود ",
                        style = MaterialTheme.typography.subtitle1,
                        color = Color(0xFF1976D2),
                    )
                }

                Spacer(modifier = Modifier.height(164.dp))

            }
        }
    )
}