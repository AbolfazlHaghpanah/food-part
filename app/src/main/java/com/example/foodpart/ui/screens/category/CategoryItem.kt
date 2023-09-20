package com.example.foodpart.ui.screens.category

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.foodpart.R

@Composable
fun CategoryItem(
    category: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    image: String?
) {

    Column(
        modifier = modifier
            .width(64.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier.then(
                if (isSelected) {
                    Modifier
                        .size(64.dp)
                        .background(
                            color = MaterialTheme.colors.secondary,
                            shape = MaterialTheme.shapes.medium
                        )
                        .border(
                            1.dp, MaterialTheme.colors.primary,
                            MaterialTheme.shapes.medium
                        )
                } else {
                    Modifier
                        .size(64.dp)
                        .background(
                            color = MaterialTheme.colors.secondary,
                            shape = MaterialTheme.shapes.medium
                        )
                }
            )

        ) {

            AsyncImage(
                model = image,
                contentDescription = "",
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .align(Alignment.Center),
                error = painterResource(id = R.drawable.category_item),
                contentScale = ContentScale.Crop

            )

        }

        Text(
            modifier = Modifier
                .height(32.dp),
            text = category,
            style = MaterialTheme.typography.body1,
            color = if (isSelected) MaterialTheme.colors.primary
            else MaterialTheme.colors.onBackground

        )


    }
}
