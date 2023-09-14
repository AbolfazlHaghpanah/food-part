package com.example.foodpart.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.foodpart.R


@Composable
fun FoodItem(
    modifier: Modifier = Modifier,
    name: String,
    time: String,
    image : String? = null
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = image,
            modifier = Modifier
                .clip(shape = MaterialTheme.shapes.large)
                .width(135.dp)
                .height(85.dp),
            contentDescription = name,
            error = painterResource(id = R.drawable.food_item),
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier
                .width(136.dp)
        ) {

            Text(
                modifier = Modifier
                    .padding(8.dp, 4.dp),
                text = name,
                style = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Start)
            )
            Text(
                modifier = Modifier.padding(8.dp, 0.dp),
                text = time,
                style = MaterialTheme.typography.subtitle1.copy(textAlign = TextAlign.Start)
            )
        }
    }
}
