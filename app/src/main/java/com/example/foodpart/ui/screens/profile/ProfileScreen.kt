package com.example.foodpart.ui.screens.profile

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
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
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.foodpart.R
import com.example.foodpart.core.AppScreens
import com.example.foodpart.core.FoodPartBottomNavigation
import com.example.foodpart.ui.components.FoodItem
import com.example.foodpart.ui.components.FoodPartButton
import com.example.foodpart.ui.components.FoodPartTextField
import com.example.foodpart.ui.components.MoreFoodItem
import com.example.foodpart.ui.components.Result
import com.example.foodpart.ui.screens.foodlist.FoodListRequestType
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val newUsername by viewModel.username.collectAsState()
    val oldPassword by viewModel.oldPassword.collectAsState()
    val newPassword by viewModel.newPassword.collectAsState()
    val isNewUsernameValid by viewModel.usernameValid.collectAsState()
    val isPasswordValid by viewModel.passwordValid.collectAsState()
    val editUserResult by viewModel.editUserResult.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var alertDialog by remember { mutableStateOf(false) }
    var isEditingUsername by remember { mutableStateOf(false) }
    var isEditingPassword by remember { mutableStateOf(false) }
    val savedFoods by viewModel.savedFoods.collectAsState()
    val user by viewModel.user.collectAsState()
    val scrollState = rememberScrollState()

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

        if (alertDialog) {
            Dialog(onDismissRequest = { alertDialog = false }) {
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .width(294.dp)
                        .height(180.dp),
                    color = MaterialTheme.colors.surface
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "آیا تمایل به خروج از حساب کاربری خود را دارید؟",
                            style = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Start),
                            modifier = Modifier
                                .width(214.dp)
                                .padding(top = 36.dp)
                                .align(Alignment.CenterHorizontally)

                        )
                        Spacer(modifier = Modifier.weight(1f))

                        Row(
                            Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            FoodPartButton(
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .width(180.dp),
                                onClick = {
                                    viewModel.logout()
                                    alertDialog = false
                                },
                                text = "خروج"
                            )

                            Button(
                                modifier = Modifier
                                    .border(
                                        1.dp,
                                        MaterialTheme.colors.primary,
                                        MaterialTheme.shapes.medium
                                    ),
                                onClick = {
                                    alertDialog = false
                                },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = MaterialTheme.colors.surface
                                ),
                                shape = MaterialTheme.shapes.medium
                            ) {
                                Text(
                                    text = "انصراف",
                                    style = MaterialTheme.typography.button,
                                    modifier = Modifier
                                        .wrapContentWidth(unbounded = true)
                                )
                            }
                        }
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(it)
                .padding(top = 40.dp, bottom = 16.dp)
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
                        .padding(start = 24.dp, end = 24.dp)
                        .width(64.dp)
                        .height(64.dp)
                        .clip(RoundedCornerShape(59.dp)),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://foodpart.samentic.com/api/files/users/${user?.id ?: ""}/${user?.avatar ?: ""}")
                        .decoderFactory(SvgDecoder.Factory())
                        .build(),
                    contentDescription = "",
                    alignment = Alignment.CenterStart,
                    error = painterResource(R.drawable.profile_photo)
                )
                Text(
                    text = user?.username ?: "مهمان",
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body2,
                )
                Spacer(modifier = Modifier.weight(1f))


                if (viewModel.isUserLoggedIn()) {
                    IconButton(onClick = {
                        alertDialog = true
                    }) {
                        Icon(
                            modifier = Modifier.padding(end = 24.dp),
                            painter = painterResource(id = R.drawable.logout_1),
                            contentDescription = "logout"
                        )
                    }

                }
            }
            if (savedFoods.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp),
                    text = "غذا های مورد علاقه",
                    style = MaterialTheme.typography.h3.copy(textAlign = TextAlign.Start)
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.Top,
                    contentPadding = PaddingValues(24.dp, 16.dp),
                ) {

                    items(if (savedFoods.size <= 4) savedFoods.size else 4) {
                        FoodItem(
                            Modifier.clickable {
                                navController.navigate(AppScreens.FoodDetails.createRoute(savedFoods[it].id))
                            },
                            name = savedFoods[it].name,
                            time = if (((savedFoods[it].readyTime ?: 0) + (savedFoods[it].cookTime
                                    ?: 0)) != 0
                            ) "${((savedFoods[it].readyTime ?: 0) + (savedFoods[it].cookTime ?: 0))} دقیقه " else "",
                            image = savedFoods[it].image
                        )
                    }
                    if (savedFoods.size > 4) {
                        item {
                            MoreFoodItem(
                                modifier = Modifier.clickable {
                                    navController.navigate(
                                        AppScreens.FoodList.createRoute(
                                            "",
                                            "غذا های مورد علاقه",
                                            FoodListRequestType.SavedFood.type
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
            }

            if (!viewModel.isUserLoggedIn()) {
                FoodPartButton(
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp),
                    onClick = {
                        navController.navigate(AppScreens.Login.route)
                    },
                    text = "وارد شوید"
                )

            } else {

                TextButton(
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp),
                    onClick = {
                        isEditingUsername = !isEditingUsername
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colors.onBackground,
                        backgroundColor = MaterialTheme.colors.background
                    )
                ) {
                    Text(
                        text = "تغییر نام کاربری",
                        style = MaterialTheme.typography.h3
                    )

                    Icon(
                        imageVector = if (isEditingUsername) Icons.Rounded.KeyboardArrowDown
                        else Icons.Rounded.KeyboardArrowLeft,
                        contentDescription = "Change username"
                    )

                }
                AnimatedVisibility(
                    visible = isEditingUsername,
                ) {
                    FoodPartTextField(
                        modifier = Modifier
                            .padding(24.dp, 8.dp),
                        value = newUsername,
                        onValueChange = {
                            viewModel.setUsername(it)
                            viewModel.nullUsernameValid()
                        },
                        placeholder = "نام کاربری جدید",
                        isError = isNewUsernameValid != null,
                        errorMassage = isNewUsernameValid
                    )

                }

                TextButton(
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp),

                    onClick = {
                        isEditingPassword = !isEditingPassword
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colors.onBackground,
                        backgroundColor = MaterialTheme.colors.background

                    )
                ) {
                    Text(
                        text = "تغییر رمز عبور",
                        style = MaterialTheme.typography.h3
                    )

                    Icon(
                        imageVector = if (isEditingPassword) Icons.Rounded.KeyboardArrowDown
                        else Icons.Rounded.KeyboardArrowLeft,
                        contentDescription = "Change username"
                    )

                }
                AnimatedVisibility(
                    visible = isEditingPassword
                ) {
                    FoodPartTextField(
                        modifier = Modifier
                            .padding(top = 8.dp, bottom = 8.dp, start = 24.dp, end = 24.dp),
                        value = oldPassword,
                        onValueChange = {
                            viewModel.setOldPassword(it)
                        },
                        placeholder = "رمز عبور فعلی"
                    )
                }
                AnimatedVisibility(
                    visible = isEditingPassword
                ) {
                    FoodPartTextField(
                        modifier = Modifier
                            .padding(top = 0.dp, bottom = 8.dp, start = 24.dp, end = 24.dp),
                        value = newPassword,
                        onValueChange = {
                            viewModel.setNewPassword(it)
                            viewModel.nullPasswordValid()
                        },
                        placeholder = "رمز عبور جدید",
                        isError = isPasswordValid != null,
                        errorMassage = isPasswordValid
                    )


                }

                Spacer(modifier = Modifier.weight(1f))

                AnimatedVisibility(
                    visible = newUsername.isNotEmpty()
                            || newPassword.isNotEmpty()
                            || oldPassword.isNotEmpty(),
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {


                    FoodPartButton(
                        modifier = Modifier
                            .padding(top = 8.dp, bottom = 8.dp, start = 24.dp, end = 24.dp),
                        onClick = {
                            scope.launch {
                                if (newUsername.isNotEmpty()) viewModel.checkUsernameValidation()
                                if (newPassword.isNotEmpty()) viewModel.checkPasswordValidation()

                                delay(50)

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

                                    while (editUserResult != Result.Success) {
                                        delay(50)
                                        if (editUserResult != Result.Loading
                                        ) {
                                            break
                                        }
                                    }

                                    when (editUserResult) {
                                        Result.Success -> {
                                            scaffoldState.snackbarHostState.showSnackbar("تغییرات با موفقیت اعمال شد")
                                            viewModel.setNewPassword("")
                                            viewModel.setUsername("")
                                            viewModel.setOldPassword("")
                                        }

                                        Result.Loading -> {}
                                        Result.Error("not_success_response") -> {
                                            scaffoldState.snackbarHostState.showSnackbar("اطلاعات وارد شده صحیح نیست")
                                        }

                                        Result.Error("no_Status") -> {
                                            scaffoldState.snackbarHostState.showSnackbar("خطا در برقراری ارتباط")
                                        }

                                        else -> {
                                            scaffoldState.snackbarHostState.showSnackbar("مشکلی پیش اومد")

                                        }


                                    }
                                }
                            }

                        },
                        text = "تایید",
                        isLoading = editUserResult == Result.Loading
                    )
                }


            }


        }
    }

}