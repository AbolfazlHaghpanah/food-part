package com.example.foodpart.ui.screens.whattocook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WhatToCookScreenViewModel @Inject constructor() : ViewModel() {
    private val _isHintShow = MutableStateFlow(true)
    val isHintShow = _isHintShow.asStateFlow()

    private val _selectedDifficultyItems: MutableStateFlow<DifficultyItems> =
        MutableStateFlow(DifficultyItems.NoMatter)
    val selectedDifficultyItems = _selectedDifficultyItems.asStateFlow()

    private val _itemsText = MutableStateFlow("")
    val itemsText = _itemsText.asStateFlow()

    private val _timeText = MutableStateFlow("")
    val timeText = _timeText.asStateFlow()

    private val _isTimeTextValid = MutableStateFlow(true)
    val isTimeTextValid = _isTimeTextValid.asStateFlow()

    private val _isItemTextValid = MutableStateFlow(true)
    val isItemTextValid = _isItemTextValid.asStateFlow()


    fun setHintShow(isHintShow: Boolean) {
        viewModelScope.launch {
            _isHintShow.emit(isHintShow)
        }
    }

    fun setSelectedDifficultyItems(difficultyItems: DifficultyItems) {
        viewModelScope.launch {
            _selectedDifficultyItems.emit(difficultyItems)
        }
    }

    fun setItemText(text: String) {
        viewModelScope.launch {
            _itemsText.emit(text)
        }
    }

    fun setTimeText(text: String) {
        viewModelScope.launch {
            _timeText.emit(text)
        }
    }

    fun setIsTimeValid(value : Boolean){
        viewModelScope.launch {
            _isTimeTextValid.emit(value)
        }
    }

    fun setIsItemValid(value : Boolean){
        viewModelScope.launch {
            _isItemTextValid.emit(value)
        }
    }

    fun getDescriptionText(): String {
        return if (selectedDifficultyItems.value != DifficultyItems.NoMatter) {
            "نتایج جستجو با${itemsText.value}" +
                    "\n در مدت زمان ${timeText.value} با درجه سختی ${selectedDifficultyItems.value.name}"

        } else {
            "نتایج جستجو با${itemsText.value}" +
                    "\n در مدت زمان ${timeText.value}"

        }
    }

}