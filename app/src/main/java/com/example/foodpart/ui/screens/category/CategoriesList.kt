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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foodpart.fooddata.Categories

@Composable
fun CategoriesList(
    viewModel: CategoryScreenViewModel = hiltViewModel()
) {
    val indicationState = remember { MutableInteractionSource() }
    val categories by viewModel.category.collectAsState()
    val selectedCategoryId by viewModel.selectedCategoryId.collectAsState()
    LazyRow(
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(categories) { item ->
            CategoryItem(
                category = item.name,
                isSelected = selectedCategoryId?.id == item.id,
                modifier = Modifier
                    .clickable(
                        interactionSource = indicationState,
                        indication = null
                    ) {
                        viewModel.setSelectedCategoryId(item)

                    },
                image = item.image
            )
        }
    }
}