package com.example.foodpart.ui.screens.fooddetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodpart.network.common.safeApi
import com.example.foodpart.network.fooddetails.FoodDetailsApi
import com.example.foodpart.network.fooddetails.FoodDetailsResponse
import com.example.foodpart.network.foodlistbycatecory.FoodListByCategoryResponse
import com.example.foodpart.ui.components.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FoodDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val foodDetailsApi: FoodDetailsApi
): ViewModel() {

    val foodId = savedStateHandle.get<String>("id") ?: ""

    private val _foodResult = MutableStateFlow<Result>(Result.Idle)
    val foodResult = _foodResult.asStateFlow()

    private val _food = MutableStateFlow<FoodDetailsResponse?>(null)
    val food = _food.asStateFlow()


    private val _foodSuggestionList = MutableStateFlow<FoodListByCategoryResponse?>(null)
    val foodSuggestionList = _foodSuggestionList.asStateFlow()
    init {
        getFood()
    }


    fun getSuggestionFoods(){
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = {
                    foodDetailsApi.getFoodListByIds(_food.value?.additionalInfo?.similarFoods?.joinToString { it }?:"")
                },
                onDataReady = {
                    _foodSuggestionList.value= it
                }
            ).collect()
        }
    }
    fun getFood() {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = {
                    foodDetailsApi.getFoodDetails(foodId)
                },
                onDataReady = {
                    _food.value = it
                    getSuggestionFoods()
                }
            ).collect(_foodResult)


        }
    }

}