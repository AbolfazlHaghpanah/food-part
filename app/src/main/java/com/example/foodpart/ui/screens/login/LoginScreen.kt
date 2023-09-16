package com.example.foodpart.ui.screens.login

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodpart.R
import com.example.foodpart.core.AppScreens
import com.example.foodpart.ui.components.FoodPartButton
import com.example.foodpart.ui.components.FoodPartTextField

@Composable
fun LoginScreen(
    navController: NavController,
) {


    val focusManager = LocalFocusManager.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isUsernameValid by remember {
        mutableStateOf(true)
    }
    var isPasswordValid by remember {
        mutableStateOf(true)
    }
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp
            )
            {
                IconButton(onClick = {
                    navController.navigate(AppScreens.Profile.route)
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
                    text = "ورود",
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Right,
                )
            }
        },
    ) { paddingValues ->

        BackHandler {
            navController.popBackStack(AppScreens.Profile.route, inclusive = false)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(start = 24.dp, end = 24.dp)
                .background(color = MaterialTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Spacer(
                modifier = Modifier
                    .height(66.dp)
            )
            Box(
                Modifier
                    .width(75.dp)
                    .height(75.dp)
                    .padding(8.dp)
                    .background(
                        color = MaterialTheme.colors.primary,
                        shape = RoundedCornerShape(size = 59.dp)
                    )
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

            Spacer(
                modifier = Modifier
                    .height(88.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = "خوش آمدید",
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.h1
                        .copy(textAlign = TextAlign.Start)
                )

                Text(
                    text = "برای ورود اطلاعات حساب خود را وارد کنید",
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.body1
                        .copy(
                            textAlign = TextAlign.Start
                        )
                )
                Spacer(modifier = Modifier.height(32.dp))

                FoodPartTextField(
                    value = username,
                    onValueChange = {
                        username = it
                        isUsernameValid = true
                    },
                    placeholder = "نام کاربری",
                    isError = !isUsernameValid,
                    errorMassage = "نام کاربری را وارد کنید",
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    })
                )
                FoodPartTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        isPasswordValid = true
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                    }),
                    placeholder = "رمز ورود",
                    isError = !isPasswordValid,
                    errorMassage = "رمز ورود را وارد کنید"
                )
                FoodPartButton(
                    onClick = {
                        focusManager.clearFocus()

                        when ("") {
                            username -> isUsernameValid = false
                            password -> isPasswordValid = false
                            else -> navController.navigate(AppScreens.Profile.route)
                        }
                    },
                    text = "تایید"
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                Text(
                    text = "حساب کاربری ندارید؟",
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onBackground,
                )

                Text(
                    modifier = Modifier
                        .clickable {
                            navController.navigate(AppScreens.SignUp.route)
                        },
                    text = " ثبت نام ",
                    style = MaterialTheme.typography.subtitle1,
                    color = Color(0xFF1976D2),
                )

                Text(
                    text = "کنید",
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onBackground,
                )
            }
        }
    }
}


