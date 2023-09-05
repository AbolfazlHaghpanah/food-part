package com.example.foodpart.ui.screens.search

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchViewModel : ViewModel() {

    private val _isError = MutableStateFlow(false)
    val isError = _isError.asStateFlow()

    private val _text = MutableStateFlow("")
    val text = _text.asStateFlow()


    fun SetText(text: String) {
        _text.value = text
    }

    fun setError(isError: Boolean) {
        _isError.value = isError
    }

}