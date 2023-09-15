package com.example.foodpart.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val foodListByCategoryApi: FoodListByCategoryApi
)  : ViewModel() {

    private val _foodList = MutableStateFlow<List<FoodListByCategoryItem>>(emptyList())
    val foodList = _foodList.asStateFlow()

    private val _result = MutableStateFlow<Result>(Result.Idle)
    val result = _result.asStateFlow()

    private val _isError = MutableStateFlow(false)
    val isError = _isError.asStateFlow()

    private val _text = MutableStateFlow<String>("")
    val text = _text.asStateFlow()


    fun emptyFoodList(){
        _foodList.value = emptyList()
    }



    fun getFoodListBySearch(){
        viewModelScope.launch (Dispatchers.IO){
            safeApi(
                call = {
                    foodListByCategoryApi.getSearchedFoodList(_text.value)
                },
                onDataReady = {
                    _foodList.value = it.data
                }
            ).collect(_result)
        }
    }

    fun setText(text: String) {
        _text.value = text
    }

    fun setError(isError: Boolean) {
        _isError.value = isError
    }



}