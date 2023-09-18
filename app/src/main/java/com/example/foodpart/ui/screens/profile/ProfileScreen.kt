package com.example.foodpart.ui.screens.profile

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.decode.Decoder
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.foodpart.R
import com.example.foodpart.core.AppScreens
import com.example.foodpart.core.FoodPartBottomNavigation
import com.example.foodpart.core.UserInfo
import com.example.foodpart.ui.components.FoodPartButton
import com.example.foodpart.ui.components.FoodPartTextField
import com.example.foodpart.ui.components.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val username = UserInfo.username?.collectAsState("مهمان")
    val newUsername by viewModel.username.collectAsState()
    val oldPassword by viewModel.oldPassword.collectAsState()
    val newPassword by viewModel.newPassword.collectAsState()
    val isNewUsernameValid by viewModel.usernameValid.collectAsState()
    val isPasswordValid by viewModel.passwordValid.collectAsState()
    var isLoading by remember { mutableStateOf(false) }
    val editUserResult by viewModel.editUserResult.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

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
                        text = if (editUserResult == Result.Success) "ثبت نام با موفقیت انجام شد"
                        else "مشکلی به وجود اومد",
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        },
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
                .padding(start = 24.dp, end = 24.dp, top = 40.dp, bottom = 16.dp)
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                Modifier
                    .padding(bottom = 17.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                AsyncImage(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .width(64.dp)
                        .height(64.dp)
                        .clip(RoundedCornerShape(59.dp)),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://foodpart.samentic.com/api/files/users/${UserInfo.id.value ?: ""}/${UserInfo.avatar.value ?: ""}")
                        .decoderFactory(SvgDecoder.Factory())
                        .build(),
                    contentDescription = "",
                    alignment = Alignment.CenterStart,
                    error = painterResource(R.drawable.profile_photo)
                )
                Text(
                    text = username?.value ?: "مهمان",
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body2,
                )
            }

            if (UserInfo.token.value ?: null == null) {
                FoodPartButton(
                    onClick = { navController.navigate(AppScreens.Login.route) },
                    text = "وارد شوید"
                )
            } else {

                Row {
                    Text(
                        text = "تغییر نام کاربری",
                        style = MaterialTheme.typography.h3
                    )

                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowDown,
                        contentDescription = "Change username"
                    )

                }

                FoodPartTextField(
                    modifier = Modifier
                        .padding(0.dp, 8.dp),
                    value = newUsername,
                    onValueChange = {
                        viewModel.setUsername(it)
                        viewModel.nullUsernameValid()
                    },
                    placeholder = "نام کاربری جدید",
                    isError = isNewUsernameValid != null,
                    errorMassage = isNewUsernameValid
                )

                Row {
                    Text(
                        text = "تغییر رمز عبور",
                        style = MaterialTheme.typography.h3
                    )

                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowDown,
                        contentDescription = "Change username"
                    )

                }

                FoodPartTextField(
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp),
                    value = oldPassword,
                    onValueChange = {
                        viewModel.setOldPassword(it)
                    },
                    placeholder = "رمز عبور فعلی"
                )

                FoodPartTextField(
                    modifier = Modifier
                        .padding(top = 0.dp, bottom = 8.dp),
                    value = newPassword,
                    onValueChange = {
                        viewModel.setNewPassword(it)
                        viewModel.nullPasswordValid()
                    },
                    placeholder = "رمز عبور جدید",
                    isError = isPasswordValid != null,
                    errorMassage = isPasswordValid
                )

                Spacer(modifier = Modifier.weight(1f))

                AnimatedVisibility(
                    visible = newUsername.isNotEmpty()
                            || newPassword.isNotEmpty()
                            || oldPassword.isNotEmpty(),
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {

                    FoodPartButton(
                        onClick = {

                            if (newUsername.isNotEmpty()) viewModel.checkUsernameValidation()
                            if (newPassword.isNotEmpty()) viewModel.checkPasswordValidation()
                            scope.launch {
                                delay(50)
                            }
                            if (isNewUsernameValid == null && isPasswordValid == null) {
                                if (
                                    newUsername.isNotEmpty()
                                    && newPassword.isNotEmpty()
                                    && oldPassword.isNotEmpty()
                                ) {
                                    viewModel.editAll()
                                } else if (
                                    newUsername.isNotEmpty()
                                ) {
                                    viewModel.editUsername()
                                } else if (
                                    newPassword.isNotEmpty()
                                    && oldPassword.isNotEmpty()
                                ) {
                                    viewModel.editPassword()
                                }
                                scope.launch {
                                    isLoading = true
                                    while (editUserResult != Result.Success) {
                                        delay(50)
                                        if (editUserResult != Result.Loading) {
                                            isLoading = false
                                            break
                                        }
                                    }
                                    if (editUserResult == Result.Success) {
                                        viewModel.setNewPassword("")
                                        viewModel.setUsername("")
                                        viewModel.setOldPassword("")
                                    }
                                    scaffoldState.snackbarHostState.showSnackbar("")

                                }
                            }

                        },
                        text = "تایید",
                        isLoading = isLoading
                    )
                }


            }


        }
    }

}