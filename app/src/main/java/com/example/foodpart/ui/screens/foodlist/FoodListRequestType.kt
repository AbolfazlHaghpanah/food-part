package com.example.foodpart.ui.screens.foodlist


sealed class FoodListRequestType(
    val type: String,
    var whatToCookIngredients: String? = null,
    var whatToCookDifficulty: Int? = null,
    var whatToCookTimeLimit: Int? = null
) {
    object Category : FoodListRequestType("category")
    object Meals : FoodListRequestType("meals")
    object WhatToCook : FoodListRequestType("what-to-cook"){
        fun createWhatToCookItems(
            ingredient: String,
            difficulties: Int?,
            timeLimit: Int
        ) {
            FoodListRequestType.WhatToCook.whatToCookDifficulty = difficulties
            FoodListRequestType.WhatToCook.whatToCookTimeLimit = timeLimit
            FoodListRequestType.WhatToCook.whatToCookIngredients = ingredient
        }
    }

}