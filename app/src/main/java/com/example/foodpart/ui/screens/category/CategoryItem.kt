package com.example.foodpart.ui.screens.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodpart.R
import com.example.foodpart.ui.theme.FoodPartTheme

@Composable
fun categoryItem(
    category: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier
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
                        .background(
                            color = MaterialTheme.colors.secondary,
                            shape = MaterialTheme.shapes.medium
                        )
                }
            )

        ) {
            Image(
                painter = painterResource(id = R.drawable.category_item),
                contentDescription = "",
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Center)

            )
        }

        Text(
            text = category,
            style = MaterialTheme.typography.subtitle1,
            color = if (isSelected) MaterialTheme.colors.primary
            else MaterialTheme.colors.onBackground

        )


    }
}
