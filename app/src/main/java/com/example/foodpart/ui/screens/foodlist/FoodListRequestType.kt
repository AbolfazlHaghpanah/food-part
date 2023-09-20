package com.example.foodpart.ui.screens.foodlist


sealed class FoodListRequestType(
    val type: String,
    var whatToCookIngredients: String? = null,
    var whatToCookDifficulty: Int? = null,
    var whatToCookTimeLimit: Int? = null,
    var whatToCookDescription : String? = null
) {
    object Category : FoodListRequestType("category")
    object Meals : FoodListRequestType("meals")
    object WhatToCook : FoodListRequestType("what-to-cook"){
        fun createWhatToCookItems(
            ingredient: String,
            difficulties: Int?,
            timeLimit: Int,
            description : String
        ) {
            whatToCookDifficulty = difficulties
            whatToCookTimeLimit = timeLimit
            whatToCookIngredients = ingredient
            whatToCookDescription = description
        }
    }
    object SavedFood : FoodListRequestType("saved-food")

}