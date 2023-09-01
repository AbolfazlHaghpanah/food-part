package com.example.foodpart

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ContentAlpha.medium
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginPage() {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color(0xFF17171A),
            )
            {
                IconButton(onClick = { }) {
                    Icon(
                        modifier = Modifier
                            .padding(1.dp)
                            .width(7.41.dp)
                            .height(12.dp),
                        tint = Color(0xFFF8F8F8),
                        painter = painterResource(R.drawable.vector1),
                        contentDescription = " "
                    )
                }
                Text(
                    text = "ورود",

                    // H2
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.iranyekan_bold)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFFF8F8F8),
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
                    .background(color = Color(0xFF17171A)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    Modifier
                        .width(64.dp)
                        .height(64.dp)
                        .background(
                            color = Color(0xFFFF6262),
                            shape = RoundedCornerShape(size = 59.dp)
                        )
                        .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(
                        10.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(1.dp)
                            .width(52.dp)
                            .height(52.dp),
                        tint = Color(0xFFFFFFFF),
                        painter = painterResource(R.drawable.logo_dark),
                        contentDescription = "ورود"
                    )
                }

                Spacer(modifier = Modifier.height(88.dp))

                Text(
                    color = Color(0xFFF8F8F8),
                    text = "خوش آمدید",
                    style = MaterialTheme.typography.h1
                        .copy(textAlign = TextAlign.Right)

                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    color = Color(0xFFE6E6E6),
                    text = "برای ورود اطلاعات حساب خود را وارد کنید",
                    style = MaterialTheme.typography.subtitle1
                        .copy(textAlign = TextAlign.Right)

                )

                Spacer(modifier = Modifier.height(43.dp))

                var username by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }

                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(85.dp),

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


                        /* Text(
                            text = "نام کاربری",

                            Modifier
                                .width(51.dp)
                                .height(20.dp),
                            style = TextStyle(
                                fontSize = 13.sp,
                                lineHeight = 19.8.sp,
                                fontFamily = FontFamily(Font(R.font.iranyekan_regular)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFFE6E6E6),
                                textAlign = TextAlign.Right,
                                letterSpacing = 0.2.sp
                            )
                        )
                    },
                    modifier = Modifier
                        .width(328.dp)
                        .height(56.dp)
                        .background(
                            color = Color(0xFF222228),
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                        .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 12.dp)
                ) */

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },

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
                    colors = ButtonDefaults.buttonColors(Color(0xFFFF6262)),
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier
                        .width(328.dp)
                        .height(48.dp)
                    //.background(color = Color(0xFFFF6262), shape = RoundedCornerShape(size = 16.dp))
                ) {
                    Text(
                        text = "تایید",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.iranyekan_bold)),
                            fontWeight = FontWeight(800),
                            color = Color(0xFFF8F8F8),
                            textAlign = TextAlign.Center
                        )
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "حساب کاربری ندارید؟ ثبت نام کنید",
                    style = TextStyle(
                        fontSize = 11.sp,
                        lineHeight = 16.7.sp,
                        fontFamily = FontFamily(Font(R.font.iranyekan_mediom)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFFF8F8F8),
                        textAlign = TextAlign.Left,
                        )
                )

                Spacer(modifier = Modifier.height(228.dp))

            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    LoginPage()
}
