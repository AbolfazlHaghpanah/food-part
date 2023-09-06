package com.example.foodpart.ui.screens.category

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodpart.fooddata.Categories

@Composable
fun CategoriesList(
    viewModel: CategoryScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
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
                    .clickable {
                        viewModel.setCategory(item)
                        viewModel.setSubCategory("")
                        viewModel.updateFoodListByCategory()
                        Log.d("TAG", "CategoryScreen: ${categorySelectedState.subCategories}")

                    }
            )
        }
    }
}