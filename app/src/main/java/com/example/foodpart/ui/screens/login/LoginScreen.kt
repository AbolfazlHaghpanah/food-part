package com.example.foodpart.ui.screens.login

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodpart.R


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background
            )
            {
                IconButton(onClick = { }) {
                    Icon(
                        modifier = Modifier
                            .padding(1.dp)
                            .width(7.41.dp)
                            .height(12.dp),
                        tint = MaterialTheme.colors.onBackground,
                        painter = painterResource(R.drawable.vector1),
                        contentDescription = "Back"
                    )
                }
                Text(
                    text = "ورود",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.iranyekan_bold)),
                        fontWeight = FontWeight(700),
                        color = MaterialTheme.colors.onBackground,
                        textAlign = TextAlign.Right,
                        letterSpacing = 0.27.sp,
                    )
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
                    text = "برای ورود اطلاعات حساب خود را وارد کنید",
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.subtitle1
                        .copy(textAlign = TextAlign.Start)
                )

                Spacer(modifier = Modifier.height(43.dp))

                var username by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }

                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(328.dp)
                        .height(56.dp),

                    placeholder = {
                        Text(
                            text = "نام کاربری",
                            style = MaterialTheme.typography.subtitle1
                        )
                    },
                    colors = TextFieldDefaults
                        .textFieldColors(focusedIndicatorColor = MaterialTheme.colors.background),
                    shape = MaterialTheme.shapes.medium
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(328.dp)
                        .height(56.dp),

                    placeholder = {
                        Text(
                            text = "رمز ورود",
                            style = MaterialTheme.typography.subtitle1
                        )
                    },
                    colors = TextFieldDefaults
                        .textFieldColors(focusedIndicatorColor = MaterialTheme.colors.background),
                    shape = MaterialTheme.shapes.medium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { },
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
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.iranyekan_bold)),
                            fontWeight = FontWeight(800),
                            color = MaterialTheme.colors.onBackground,
                            textAlign = TextAlign.Center
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    Modifier
                        .padding(start = 165.dp)
                ) {
                    Text(
                        text = "حساب کاربری ندارید؟",
                        style = MaterialTheme.typography.subtitle2,
                        color = MaterialTheme.colors.onBackground,
                    )

                    Text(
                        text = " ثبت نام ",
                        style = MaterialTheme.typography.subtitle2,
                        color = Color(0xFF1976D2),
                    )

                    Text(
                        text = "کنید",
                        style = MaterialTheme.typography.subtitle2,
                        color = MaterialTheme.colors.onBackground,
                    )
                }

                Spacer(modifier = Modifier.height(228.dp))

            }
        }
    )
}



