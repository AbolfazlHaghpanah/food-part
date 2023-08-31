package com.example.foodpart.ui.screens.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class CategoryScreenViewModel:ViewModel() {
    private val _categoryFlow = MutableStateFlow("")
    val categoryFlow = _categoryFlow.asStateFlow()

    private val _subCategoryFlow = MutableStateFlow("")
    val subCategoryFlow = _subCategoryFlow.asStateFlow()

    fun setCategory (category: String){
        _categoryFlow.value = category
    }
    fun setSubCategory (subCategory: String){
        _subCategoryFlow.value = subCategory
    }

}