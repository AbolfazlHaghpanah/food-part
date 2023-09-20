package com.example.foodpart.ui.screens.category

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SubCategoriesList(
    viewModel: CategoryScreenViewModel = hiltViewModel(),
) {

    val category by viewModel.selectedCategory.collectAsState()
    val subCategoryState by viewModel.selectedSubCategoryId.collectAsState()

    LazyRow(
        contentPadding = PaddingValues(16.dp, 2.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(category?.subCategories?: emptyList()) { item ->
            Chip(
                onClick = {
                    if (subCategoryState == item.id) {
                        viewModel.setSelectedSubCategoryId("")
                    } else {
                        viewModel.setSelectedSubCategoryId(item.id)
                    }
                    viewModel.getFoodList()

                },
                border = if (subCategoryState == item.id) {
                    BorderStroke(
                        color = MaterialTheme.colors.primary,
                        width = 1.dp
                    )
                } else {
                    null
                }
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.subtitle1,
                    color = if (subCategoryState == item.id) MaterialTheme.colors.primary
                    else MaterialTheme.colors.onBackground
                )
            }

        }



    }

    if (category?.subCategories != null){
        Spacer(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colors.onBackground)
        )
    }


}