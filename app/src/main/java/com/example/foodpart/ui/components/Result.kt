package com.example.foodpart.ui.components

sealed class Result{
        object Idle : Result()
        object Loading : Result()
        data class Error(val message: String ) : Result()
        object Success : Result()

}