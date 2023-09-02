package com.example.foodpart.ui.screens.fooddetails

import android.view.Menu
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MenuDefaults
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodpart.core.AppScreens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun foodDetailsAppBar(
    navController: NavController,
    bottomSheetState: ModalBottomSheetState,
) {
    val scope = rememberCoroutineScope()
    val menuState = remember {
        mutableStateOf(false)
    }

    TopAppBar(
        backgroundColor = MaterialTheme.colors.background
    ) {
        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Icon(imageVector = Icons.Rounded.KeyboardArrowRight, contentDescription = "Back")
        }
        Text(
            text = "اطلاعات غذا",
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.weight(1F))
        Box {
            IconButton(onClick = {
                menuState.value = true
            }) {
                Icon(
                    imageVector = Icons.Rounded.MoreVert,
                    contentDescription = "Menu",
                    Modifier.rotate(90f)
                )
            }
            DropdownMenu(
                modifier = Modifier
                    .width(160.dp)
                    .background(
                        color = MaterialTheme.colors.surface,
                        shape = MaterialTheme.shapes.small
                    ),
                expanded = menuState.value,
                onDismissRequest = { menuState.value = false }
            ) {
                DropdownMenuItem(onClick = {
                        scope.launch {
                            bottomSheetState.show()
                        }
                    menuState.value = false
                }) {
                    Icon(
                        imageVector = Icons.Rounded.Warning,
                        contentDescription = "Report"
                    )
                    Text(
                        text = "گزارش",
                        style = MaterialTheme.typography.subtitle1
                    )
                }
                DropdownMenuItem(onClick = {
                    menuState.value = false
                }) {
                    Icon(
                        imageVector = Icons.Rounded.Share,
                        contentDescription = "Share"
                    )
                    Text(
                        text = "ارسال",
                        style = MaterialTheme.typography.subtitle1
                    )
                }
                DropdownMenuItem(
                    onClick = {
                        menuState.value = false
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = "Save"
                    )
                    Text(
                        text = "ذخیره",
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            }

        }

    }

}