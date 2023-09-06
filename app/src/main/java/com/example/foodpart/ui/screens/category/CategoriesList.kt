package com.example.foodpart.ui.screens.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodpart.fooddata.Categories

@Composable
fun CategoriesList(
    viewModel: CategoryScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val indicationState = remember { MutableInteractionSource() }
    val categorySelectedState by viewModel.categoryFlow.collectAsState()
    LazyRow(
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(Categories.values()) { item ->
            CategoryItem(
                category = item.category,
                isSelected = categorySelectedState == item,
                modifier = Modifier
                    .clickable(
                        interactionSource = indicationState,
                        indication = null
                    ) {
                        viewModel.setCategory(item)
                        viewModel.setSubCategory("")
                        viewModel.updateFoodListByCategory()

                    }
            )
        }
    }
}