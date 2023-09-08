package com.example.foodpart.core

import com.example.foodpart.R

sealed class BottomNavigationItems(
    val label: String,
    val icon: Int,
    val route: String
) {
    object Category :
        BottomNavigationItems("دسته بندی", R.drawable.restaurant, AppScreens.Category.route)

    object WhatToCook :
        BottomNavigationItems("چی بپزم؟", R.drawable.restaurant_menu, AppScreens.WhatToCook.route)

    object Search : BottomNavigationItems("جستجو", R.drawable.search, AppScreens.Search.route)
    object Profile :
        BottomNavigationItems("حساب کاربری", R.drawable.person_outline, AppScreens.Profile.route)

}

val bottomNavItems = listOf(
    BottomNavigationItems.Category,
    BottomNavigationItems.WhatToCook,
    BottomNavigationItems.Search,
    BottomNavigationItems.Profile
)
