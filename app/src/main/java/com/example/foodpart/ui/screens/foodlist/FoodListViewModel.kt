package com.example.foodpart.ui.screens.foodlist

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodpart.database.savedfood.SavedFoodDao
import com.example.foodpart.network.common.safeApi
import com.example.foodpart.network.foodlistbycatecory.FoodListByCategoryApi
import com.example.foodpart.network.foodlistbycatecory.FoodListByCategoryItem
import com.example.foodpart.ui.components.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log


@HiltViewModel
class FoodListViewModel @Inject constructor(
    private val foodListByCategoryApi: FoodListByCategoryApi,
    private val savedFoodDao: SavedFoodDao,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val categoryId = savedStateHandle.get<String>("category-id")

    private val foodListRequestType = savedStateHandle.get<String>("request-type")
    val appBarText = savedStateHandle.get<String>("appbar")

    private val _description = MutableStateFlow<String?>("")
    val description = _description.asStateFlow()

    private val _foodList = MutableStateFlow<List<FoodListByCategoryItem>>(emptyList())
    val foodList = _foodList.asStateFlow()

    private val _foodListResult = MutableStateFlow<Result>(Result.Idle)
    val foodListResult = _foodListResult.asStateFlow()


    init {
        getFoodList()
    }

    fun getFoodList() {
        when (foodListRequestType) {
            FoodListRequestType.Category.type -> getFoodListByCategory()
            FoodListRequestType.Meals.type -> getFoodListByMeals()
            FoodListRequestType.WhatToCook.type -> {
                getWhatToCookFoodList()
                _description.value = FoodListRequestType.WhatToCook.whatToCookDescription
            }
            FoodListRequestType.SavedFood.type -> observeSavedFoods()
        }
    }


    fun observeSavedFoods(){
        viewModelScope.launch {
            savedFoodDao.observeFoods().collect(
                {
                    _foodList.emit(it.map { it.toFoodListByCategoryItem() })
                }
            )
        }
    }


    fun getFoodListByCategory() {

        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = {
                    foodListByCategoryApi.getFoodListByCategory(categoryId ?: "")
                },
                onDataReady = {
                    _foodList.value = it.data
                }
            ).collect(_foodListResult)

        }
    }

    fun getFoodListByMeals() {

        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = {
                    foodListByCategoryApi.getFoodListByMeals(categoryId ?: "")
                },
                onDataReady = {
                    _foodList.value = it.data
                }
            ).collect(_foodListResult)

        }
    }

    fun getWhatToCookFoodList() {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = {
                    foodListByCategoryApi.getWhatToCookFoodList(
                        ingredients = FoodListRequestType.WhatToCook.whatToCookIngredients ?: "",
                        difficulty = FoodListRequestType.WhatToCook.whatToCookDifficulty,
                        timeLimit = FoodListRequestType.WhatToCook.whatToCookTimeLimit ?: 0
                    )
                },
                onDataReady = {
                    _foodList.value = it.data
                }
            ).collect(_foodListResult)
        }
    }


}