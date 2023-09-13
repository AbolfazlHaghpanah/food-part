package com.example.foodpart.ui.screens.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodpart.network.category.CategoryApi
import com.example.foodpart.network.category.CategoryDataResponse
import com.example.foodpart.network.category.SubCategoryResponse
import com.example.foodpart.network.common.safeApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import com.example.foodpart.ui.components.Result
import kotlinx.coroutines.launch

@HiltViewModel
class CategoryScreenViewModel @Inject constructor(
    private val categoryApi: CategoryApi
) : ViewModel() {



    private val _selectedCategoryId: MutableStateFlow<CategoryDataResponse?> = MutableStateFlow(null)
    val selectedCategoryId = _selectedCategoryId.asStateFlow()

    private val _selectedSubCategoryId: MutableStateFlow<String> = MutableStateFlow("")
    val selectedSubCategoryId = _selectedSubCategoryId.asStateFlow()


    private val _subCategories = MutableStateFlow<List<SubCategoryResponse>>(emptyList())
    val subCategories = _subCategories.asStateFlow()

    private val _categoryResult = MutableStateFlow<Result>(Result.Idle)
    val categoryResult = _categoryResult.asStateFlow()


    private val _category = MutableStateFlow<List<CategoryDataResponse>>(emptyList())
    val category = _category.asStateFlow()







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
                }
            ).collect(_categoryResult)
        }
    }



    fun setSelectedCategoryId(categoryDataResponse: CategoryDataResponse) {
        _selectedCategoryId.value = categoryDataResponse
    }

    fun setSelectedSubCategoryId(id : String) {
        _selectedSubCategoryId.value = id
    }




//    fun setCategory(category: Categories) {
//        _categoryFlow.value = category
//    }
//
//    fun setSubCategory(subCategory: String) {
//        _subCategoryFlow.value = subCategory
//    }
//
//    fun updateFoodListByCategory() {
//        _foodListByCategoryFlow.value = foodList.filter {
//
//            if (categoryFlow.value.subCategories == null || subCategoryFlow.value == "")
//                it.category == categoryFlow.value
//            else
//                it.category == categoryFlow.value&& it.subCategory.contains(subCategoryFlow.value)
//
//        }
//    }
//
//    init {
//        updateFoodListByCategory()
//    }


}