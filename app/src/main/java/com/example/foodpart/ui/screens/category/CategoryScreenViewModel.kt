package com.example.foodpart.ui.screens.category

import androidx.lifecycle.ViewModel
import com.example.foodpart.fooddata.Categories
import com.example.foodpart.fooddata.foodList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CategoryScreenViewModel : ViewModel() {
    private val _categoryFlow :MutableStateFlow<Categories> = MutableStateFlow(Categories.MAIN)
    val categoryFlow = _categoryFlow.asStateFlow()

    private val _subCategoryFlow = MutableStateFlow("")
    val subCategoryFlow = _subCategoryFlow.asStateFlow()

    private val _foodListByCategoryFlow = MutableStateFlow(foodList)
    val foodListByCategoryFlow = _foodListByCategoryFlow.asStateFlow()


    fun setCategory(category: Categories) {
        _categoryFlow.value = category
    }

    fun setSubCategory(subCategory: String) {
        _subCategoryFlow.value = subCategory
    }

    fun updateFoodListByCategory() {
        _foodListByCategoryFlow.value = foodList.filter {

            if (categoryFlow.value.subCategories == null || subCategoryFlow.value == "")
                it.category == categoryFlow.value
            else
                it.category == categoryFlow.value&& it.subCategory.contains(subCategoryFlow.value)

        }
    }

    init {
        updateFoodListByCategory()
    }


}