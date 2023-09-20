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
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.foodpart.R
import com.example.foodpart.core.AppScreens
import com.example.foodpart.ui.components.FoodPartButton
import com.example.foodpart.ui.components.FoodPartTextField
import com.example.foodpart.core.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginScreenViewModel = hiltViewModel()
) {
    val loginResult by viewModel.userLoginResult.collectAsState()
    val username by viewModel.username.collectAsState()
    val password by viewModel.password.collectAsState()
    val isUsernameValid by viewModel.isUsernameValid.collectAsState()
    val isPasswordValid by viewModel.isPasswordValid.collectAsState()
    val isUserInfoValid by viewModel.isUserInfoTrue.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current




    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(it) {
                Snackbar(
                    modifier = Modifier
                        .padding(bottom = 85.dp, start = 8.dp, end = 8.dp),
                    contentColor = MaterialTheme.colors.onBackground,
                    backgroundColor = MaterialTheme.colors.secondary,
                ) {
                    Text(
                        text = it.message,
                        style = MaterialTheme.typography.caption
                    )
                }
            }

        },
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
                        viewModel.setUsername(it)
                        viewModel.setIsUsernameValid(true)
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
                        viewModel.setPassword(it)
                        viewModel.setIsPasswordValid(true)
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
                            username -> viewModel.setIsUsernameValid(false)
                            password -> viewModel.setIsPasswordValid(false)
                            else -> {
                                scope.launch {
                                    viewModel.loginUser()
                                    while (loginResult != Result.Success) {
                                        delay(100)
                                        if (!isUserInfoValid || loginResult != Result.Loading
                                        ) {
                                            break
                                        }
                                    }

                                    when (loginResult) {
                                        Result.Success -> {
                                            navController.popBackStack(
                                                AppScreens.Profile.route,
                                                false
                                            )
                                        }
                                        Result.Error("no_Status") -> {
                                            scaffoldState.snackbarHostState.showSnackbar("خطا در برقراری ارتباط")
                                        }

                                        Result.Error("not_success_response") -> {
                                            scaffoldState.snackbarHostState.showSnackbar("نام کاربری یا رمز عبور اشتباه است")
                                        }
                                        Result.Loading -> {}

                                        else -> {
                                            scaffoldState.snackbarHostState.showSnackbar("مشکلی پیش اومد")
                                        }

                                    }

                                }
                            }

                        }


                    },
                    text = "تایید",
                    isLoading = loginResult == Result.Loading
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


