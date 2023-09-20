package com.example.foodpart.ui.screens.signup

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
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.foodpart.R
import com.example.foodpart.core.AppScreens
import com.example.foodpart.ui.components.FoodPartButton
import com.example.foodpart.ui.components.FoodPartTextField
import com.example.foodpart.ui.components.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    val scaffoldState = rememberScaffoldState()
    val registerResult by viewModel.userRegisterResult.collectAsState()
    val username by viewModel.username.collectAsState()
    val password by viewModel.password.collectAsState()
    var repeatPass by remember { mutableStateOf("") }
    val isPasswordValid by viewModel.passwordValid.collectAsState()
    val isUsernameValid by viewModel.usernameValid.collectAsState()
    val isRepeatPasswordValid by viewModel.repeatPasswordValid.collectAsState()
    var isLoading by remember { mutableStateOf(false) }

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(it) {
                Snackbar(
                    modifier = Modifier
                        .padding(bottom = 85.dp, start = 8.dp, end = 8.dp),
                    contentColor = MaterialTheme.colors.onBackground,
                    backgroundColor = MaterialTheme.colors.secondary,
                    action = {
                        if (registerResult != Result.Error("no_status")) {
                            TextButton(onClick = {
                                navController.navigate(
                                    AppScreens.Login.route
                                )
                            }) {
                                Text(
                                    text = "ورود",
                                    style = MaterialTheme.typography.caption,
                                    color = MaterialTheme.colors.primary
                                )
                            }
                        }
                    },

                    ) {
                    Text(
                        text = if (registerResult == Result.Error("no_status")) "مشکل در برقراری ارتباط"
                        else "ثبت نام با موفقیت انجام شد"

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





            FoodPartTextField(
                value = username,
                onValueChange = {
                    viewModel.setUsername(it)
                    viewModel.nullUsernameValid()
                },
                placeholder = "نام کاربری",
                isError = isUsernameValid != null,
                errorMassage = isUsernameValid,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                })
            )

            Spacer(modifier = Modifier.height(8.dp))

            FoodPartTextField(
                value = password,
                onValueChange = {
                    viewModel.setPassword(it)
                    viewModel.nullPasswordValid()
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                placeholder = "رمز عبور",
                isError = isPasswordValid != null,
                errorMassage = isPasswordValid,
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                })
            )

            Spacer(modifier = Modifier.height(8.dp))

            FoodPartTextField(
                value = repeatPass,
                onValueChange = {
                    repeatPass = it
                    viewModel.nullRepeatPasswordValid()
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                placeholder = "تکرار رمز عبور",
                isError = isRepeatPasswordValid != null,
                errorMassage = isRepeatPasswordValid,
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                })
            )

            Spacer(modifier = Modifier.height(8.dp))

            FoodPartButton(
                onClick = {
                    focusManager.clearFocus()

                    viewModel.checkUsernameValidation()
                    viewModel.checkPasswordValidation()
                    viewModel.checkRepeatPasswordValidation(password, repeatPass)
                    scope.launch {
                        delay(50)
                    }
                    if (
                        isPasswordValid == null && isUsernameValid == null && isRepeatPasswordValid == null
                    ) {

                        viewModel.registerUser()
                        scope.launch {
                            isLoading = true
                            while (registerResult != Result.Success) {
                                delay(50)
                                if (registerResult != Result.Loading
                                    || registerResult == Result.Error("no_status")
                                ) {
                                    isLoading = false
                                    break
                                }
                            }
                            isLoading = false
                            if (registerResult == Result.Success
                                ||registerResult == Result.Error("no_status")) {

                                scaffoldState.snackbarHostState.showSnackbar("")

                                if (registerResult == Result.Success){
                                    navController.popBackStack(AppScreens.Login.route, false)
                                }
                            }

                        }
                    }
                },
                text = "تایید",
                isLoading = isLoading
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .align(Alignment.End)
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
        }
    }
}

