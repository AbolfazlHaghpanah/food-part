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

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SubCategoriesList(
    viewModel: CategoryScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    val categorySelectedState by viewModel.categoryFlow.collectAsState()
    val subCategoryState by viewModel.subCategoryFlow.collectAsState()
    LazyRow(
        contentPadding = PaddingValues(16.dp, 2.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(categorySelectedState.subCategories ?: listOf()) { item ->
            Chip(
                onClick = {
                    if (subCategoryState == item) {
                        viewModel.setSubCategory("")
                    } else {
                        viewModel.setSubCategory(item)
                    }
                    viewModel.updateFoodListByCategory()
                },
                border = if (subCategoryState == item) {
                    BorderStroke(
                        color = MaterialTheme.colors.primary,
                        width = 1.dp
                    )
                } else {
                    null
                }
            ) {
                Text(
                    text = item,
                    style = MaterialTheme.typography.subtitle1,
                    color = if (subCategoryState == item) MaterialTheme.colors.primary
                    else MaterialTheme.colors.onBackground
                )
            }

        }



    }

    if (categorySelectedState.subCategories!=null){
        Spacer(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colors.onBackground)
        )
    }


}