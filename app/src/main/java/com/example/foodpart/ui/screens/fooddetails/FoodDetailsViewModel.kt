package com.example.foodpart.ui.screens.fooddetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodpart.database.savedfood.SavedFoodDao
import com.example.foodpart.database.savedfood.SavedFoodEntity
import com.example.foodpart.database.user.UserDao
import com.example.foodpart.database.user.UserEntity
import com.example.foodpart.network.common.safeApi
import com.example.foodpart.network.fooddetails.FoodDetailsApi
import com.example.foodpart.network.fooddetails.FoodDetailsResponse
import com.example.foodpart.network.fooddetails.ReportBody
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
    private val foodDetailsApi: FoodDetailsApi,
    private val savedFoodDao: SavedFoodDao,
    private val userDao: UserDao
) : ViewModel() {

    private val _user = MutableStateFlow<UserEntity?>(null)

    val foodId = savedStateHandle.get<String>("id") ?: ""

    private val _foodResult = MutableStateFlow<Result>(Result.Idle)
    val foodResult = _foodResult.asStateFlow()

    private val _food = MutableStateFlow<FoodDetailsResponse?>(null)
    val food = _food.asStateFlow()
    
    private val _reportFoodText = MutableStateFlow<String>("")
    val reportFoodText = _reportFoodText.asStateFlow()

    private val _foodSuggestionList = MutableStateFlow<FoodListByCategoryResponse?>(null)
    val foodSuggestionList = _foodSuggestionList.asStateFlow()

    private val _reportFoodResult = MutableStateFlow<Result>(Result.Idle)
    val reportFoodResult = _reportFoodResult.asStateFlow()

    private val _isFullScreenImage = MutableStateFlow(false)
    val isFullScreenImage = _isFullScreenImage.asStateFlow()


    fun setIsFullScreenImage(value: Boolean){
        viewModelScope.launch{
            _isFullScreenImage.emit(value)
        }
    }

    fun setReportFoodText(value: String) {
        viewModelScope.launch {
            _reportFoodText.emit(value)
        }
    }


    init {
        getFood()
        observeUser()
    }


    fun saveFood(){
        viewModelScope.launch {
            savedFoodDao.addFood(SavedFoodEntity(
                id = foodId,
                name = food.value?.data?.name?:"",
                image = food.value?.data?.image,
                cookTime = food.value?.data?.cookTime,
                readyTime = food.value?.data?.readyTime
            ))
        }
    }


    fun getSuggestionFoods() {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = {
                    foodDetailsApi.getFoodListByIds(_food.value?.additionalInfo?.similarFoods?.joinToString { it }
                        ?: "")
                },
                onDataReady = {
                    _foodSuggestionList.value = it
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

    fun observeUser(){
        viewModelScope.launch {
            userDao.observeUser().collect(_user)
        }
    }

    fun isUserLoggedIn():Boolean{
        return _user.value != null
    }

    fun reportFood() {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = {
                    foodDetailsApi.reportFood(
                        foodId,
                        ReportBody(reportFoodText.value),
                        "Bearer ${_user.value?.token}"
                    )
                },
                onDataReady = {
                    Log.d("report", "reportFood: success")
                }
            ).collect(_reportFoodResult)
        }
    }

}