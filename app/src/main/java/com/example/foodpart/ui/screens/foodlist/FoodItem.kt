package com.example.foodpart.ui.screens.foodlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodpart.R
import com.example.foodpart.ui.theme.FoodPartTheme


@Composable
fun foodItem(
    modifier: Modifier = Modifier,
    name: String,
    time: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .clip(shape = MaterialTheme.shapes.large)
                .width(136.dp)
                .height(84.dp),
            painter = painterResource(id = R.drawable.food_item),
            contentDescription = "",
            contentScale = ContentScale.Crop,

            )
        Column(
            modifier = Modifier
                .width(136.dp)
        ) {

            Text(
                modifier = Modifier.padding(8.dp, 4.dp),
                text = name,
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                modifier = Modifier.padding(8.dp, 4.dp),
                text = time,
                style = MaterialTheme.typography.subtitle2
            )
        }
    }
}
