package com.example.foodpart.core

import android.icu.lang.UCharacterCategory

sealed class AppScreens (val route : String){

    object Category : AppScreens("category")

    object FoodDetails : AppScreens("food-details/{id}"){
        fun createRoute(id:Int) : String{
            return "food-details/$id"
        }
    }

    object Login : AppScreens("login")

    object Profile : AppScreens("profile")

    object Search : AppScreens("search")

    object WhatToCook : AppScreens("what-to-cook")

    object FoodList : AppScreens("food-list/{category}/{appbar}"){
        fun createRoute (category: String , appBar : String):String {
            return "food-list/$category/$appBar"
        }
    }


}