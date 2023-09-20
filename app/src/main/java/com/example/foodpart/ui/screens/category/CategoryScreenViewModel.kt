package com.example.foodpart.ui.screens.category

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodpart.network.category.CategoryApi
import com.example.foodpart.network.category.CategoryDataResponse
import com.example.foodpart.network.common.safeApi
import com.example.foodpart.network.foodlistbycatecory.FoodListByCategoryApi
import com.example.foodpart.network.foodlistbycatecory.FoodListByCategoryItem
import com.example.foodpart.core.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryScreenViewModel @Inject constructor(
    private val categoryApi: CategoryApi,
    private val foodListByCategoryApi: FoodListByCategoryApi
) : ViewModel() {


    private val _selectedCategory: MutableStateFlow<CategoryDataResponse?> = MutableStateFlow(null)
    val selectedCategory = _selectedCategory.asStateFlow()

    private val _selectedSubCategoryId: MutableStateFlow<String> = MutableStateFlow("")
    val selectedSubCategoryId = _selectedSubCategoryId.asStateFlow()


    private val _categoryResult = MutableStateFlow<Result>(Result.Idle)
    val categoryResult = _categoryResult.asStateFlow()


    private val _category = MutableStateFlow<List<CategoryDataResponse>>(emptyList())
    val category = _category.asStateFlow()

    private val _foodList = MutableStateFlow<List<FoodListByCategoryItem>?>(null)
    val foodList = _foodList.asStateFlow()

    private val _foodListResult = MutableStateFlow<Result>(Result.Idle)
    val foodListResult = _foodListResult.asStateFlow()


    init {
        getCategory()


    }

    fun getCategory() {
        viewModelScope.launch {
            safeApi(
                call = {
                    categoryApi.getCategory()
                },
                onDataReady = {
                    _category.value = it.data
                    _selectedCategory.value = _category.value[0]
                    getFoodList()
                }
            ).collect(_categoryResult)
        }

    }

    fun setSelectedCategory(categoryDataResponse: CategoryDataResponse) {
        viewModelScope.launch {
            _selectedCategory.emit(categoryDataResponse)

        }
    }

    fun setSelectedSubCategoryId(id: String) {

        viewModelScope.launch {
            _selectedSubCategoryId.emit(id)
        }
    }


    fun getFoodList() {
        viewModelScope.launch {

            safeApi(
                call = {
                    Log.d("request", " sab : ${selectedSubCategoryId.value}")
                    Log.d("request", " cat : ${selectedCategory.value?.id ?: "nulle"}")

                    if (selectedSubCategoryId.value.isNotEmpty()) {
                        foodListByCategoryApi
                            .getFoodListByCategory(selectedSubCategoryId.value)
                    } else {
                        foodListByCategoryApi
                            .getFoodListByCategory(
                                selectedCategory.value?.id ?: ""
                            )
                    }

                },
                onDataReady = {
                    viewModelScope.launch(Dispatchers.IO) {
                        _foodList.emit(it.data)
                    }
                }
            ).collect(_foodListResult)
        }
    }


}
