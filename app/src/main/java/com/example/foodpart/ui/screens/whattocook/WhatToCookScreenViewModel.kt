package com.example.foodpart.ui.screens.whattocook

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WhatToCookScreenViewModel : ViewModel() {
    private val _isHintShow = MutableStateFlow(true)
    val isHintShow = _isHintShow.asStateFlow()

    private val _selectedDifficultyItems: MutableStateFlow<DifficultyItems> =
        MutableStateFlow(DifficultyItems.NoMatter)
    val selectedDifficultyItems = _selectedDifficultyItems.asStateFlow()

    private val _itemsText = MutableStateFlow("")
    val itemsText = _itemsText.asStateFlow()

    private val _timeText = MutableStateFlow("")
    val timeText = _timeText.asStateFlow()


    fun setHintShow(isHintShow: Boolean) {
        _isHintShow.value = isHintShow
    }

    fun setSelectedDifficultyItems(difficultyItems: DifficultyItems) {
        _selectedDifficultyItems.value = difficultyItems
    }

    fun setItemText(text: String) {
        _itemsText.value = text
    }

    fun setTimeText(text: String) {
        _timeText.value = text
    }

    fun getDescriptionText(): String {
        return if (timeText.value.isNotEmpty()) {
            "نتایج جستجو با${itemsText.value}" +
                    "\n در مدت زمان ${timeText.value} با درجه سختی ${selectedDifficultyItems.value.name}"

        } else {
            "نتایج جستجو با ${itemsText.value}\n" +
                    "با درجه سختی ${selectedDifficultyItems.value.name}"
        }
    }

}