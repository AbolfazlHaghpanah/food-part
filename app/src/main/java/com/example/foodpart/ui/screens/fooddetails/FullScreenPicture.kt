package com.example.foodpart.ui.screens.fooddetails

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun FullScreenPicture(
    isFullImage: MutableState<Boolean>,
    imageRes: Int

) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background
            ) {
                IconButton(onClick = {
                    isFullImage.value = false
                }) {
                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowRight,
                        contentDescription = "Back",
                        tint = MaterialTheme.colors.onBackground
                    )
                }
                Text(
                    text = "عکس",
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.onBackground
                )
                Spacer(modifier = Modifier.weight(1F))
                IconButton(onClick = {

                }) {
                    Icon(
                        imageVector = Icons.Rounded.Share,
                        contentDescription = "Save",
                        tint = MaterialTheme.colors.onBackground
                    )
                }
            }
        }
    ) {
        BackHandler {
            isFullImage.value = false
        }
        AnimatedVisibility(
            visible = isFullImage.value,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = { isFullImage.value = false }
                    )
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(),
                    painter = painterResource(imageRes),
                    contentDescription = "food image",
                    contentScale = ContentScale.Crop
                )
            }
        }

    }
}