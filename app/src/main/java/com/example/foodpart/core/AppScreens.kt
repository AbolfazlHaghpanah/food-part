package com.example.foodpart.core

import android.os.Parcelable
import com.example.foodpart.ui.screens.foodlist.FoodListRequestType

sealed class AppScreens(val route: String) {

    object Category : AppScreens("category")

    object FoodDetails : AppScreens("food-details?id={id}") {
        fun createRoute(id: String): String {
            return "food-details?id=$id"
        }
    }

    object Login : AppScreens("login")

    object Profile : AppScreens("profile")

    object Search : AppScreens("search")

    object WhatToCook : AppScreens("what-to-cook")

    object FoodList : AppScreens("food-list?category-id={category-id}&request-type={request-type}&appbar={appbar}") {
        fun createRoute(category: String, appBar: String,requestType: String): String {
            return "food-list?category-id=$category&request-type=$requestType&appbar=$appBar"
        }
    }

    object SignUp : AppScreens("sign-up")


}
