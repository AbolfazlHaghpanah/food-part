package com.example.foodpart.ui.screens.fooddetails

import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.foodpart.core.AppScreens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FoodDetailsAppBar(
    navController: NavController,
    bottomSheetState: ModalBottomSheetState,
    scaffoldState: ScaffoldState,
    viewModel: FoodDetailsViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val menuState = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    ) {
        IconButton(onClick = {
            navController.popBackStack(
                route = AppScreens.Category.route,
                inclusive = false
            )
        }) {
            Icon(
                imageVector = Icons.Rounded.KeyboardArrowRight,
                contentDescription = "Back",
                tint = MaterialTheme.colors.onBackground
            )
        }
        Text(
            text = "اطلاعات غذا",
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onBackground
        )
        Spacer(modifier = Modifier.weight(1F))
        Box {
            IconButton(onClick = {
                menuState.value = true
            }) {
                Icon(
                    imageVector = Icons.Rounded.MoreVert,
                    contentDescription = "Menu",
                    Modifier.rotate(90f),
                    tint = MaterialTheme.colors.onBackground
                )
            }
            DropdownMenu(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.surface,
                    )
                    .width(160.dp),
                expanded = menuState.value,
                onDismissRequest = { menuState.value = false }
            ) {
                DropdownMenuItem(onClick = {
                    scope.launch {

                        if (viewModel.isUserLoggedIn())
                        {
                            bottomSheetState.show()
                        }else{
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "برای گزارش ابتدا باید وارد شوید"
                            )
                        }

                    }
                    menuState.value = false
                }) {
                    Icon(
                        imageVector = Icons.Rounded.Warning,
                        contentDescription = "Report"
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 8.dp),
                        text = "گزارش",
                        style = MaterialTheme.typography.body1
                    )
                }
                DropdownMenuItem(onClick = {
                    val sendIntent: Intent = Intent().apply {
                        this.action = Intent.ACTION_SEND
                        this.putExtra(Intent.EXTRA_TEXT, "Sharing a food from Food Part")
                        type = "text/plain"
                    }
                    val bundle: Bundle = Bundle.EMPTY

                    startActivity(context, sendIntent, bundle)
                    menuState.value = false
                }) {
                    Icon(
                        imageVector = Icons.Rounded.Share,
                        contentDescription = "Share"
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 8.dp),
                        text = "ارسال",
                        style = MaterialTheme.typography.body1
                    )
                }
                DropdownMenuItem(
                    onClick = {
                        scope.launch {
                            viewModel.saveFood()
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "دستور به علاقه مندی ها اضافه شد",
                                actionLabel = "علاقه مندی ها"
                            )
                        }
                        menuState.value = false
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = "Save"
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 8.dp),
                        text = "ذخیره",
                        style = MaterialTheme.typography.body1
                    )
                }
            }

        }


    }

}