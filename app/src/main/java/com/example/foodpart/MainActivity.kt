package com.example.foodpart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.foodpart.core.AppScreens
import com.example.foodpart.core.foodPartBottomNavigation
import com.example.foodpart.ui.screens.category.CategoryScreenViewModel
import com.example.foodpart.ui.screens.category.categoryScreen
import com.example.foodpart.ui.screens.fooddetails.foodDetailsScreen
import com.example.foodpart.ui.screens.foodlist.foodListScreen
import com.example.foodpart.ui.screens.login.LoginScreen
import com.example.foodpart.ui.screens.profile.profileScreen
import com.example.foodpart.ui.screens.search.searchScreen
import com.example.foodpart.ui.screens.whattocook.whatToCookScreen
import com.example.foodpart.ui.theme.FoodPartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodPartTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        foodPartBottomNavigation(navController = navController)
                    }
                ) {
                    Column(
                        Modifier.padding(it)
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = AppScreens.Category.route
                        ) {
                            mainNavGraph(navController)
                        }
                    }
                }
            }
        }
    }
}

private fun NavGraphBuilder.mainNavGraph(
    navController: NavController
) {
    composable(AppScreens.Category.route) {
        categoryScreen(
            navController = navController,
            viewModel = CategoryScreenViewModel()
        )
    }

    composable(AppScreens.Profile.route) {
        profileScreen(navController = navController)
    }

    composable(AppScreens.Login.route) {
        LoginScreen(navController = navController)
    }

    composable(AppScreens.Search.route) {
        searchScreen(navController = navController)
    }

    composable(AppScreens.WhatToCook.route) {
        whatToCookScreen(navController = navController)
    }

    composable(
        route = AppScreens.FoodDetails.route,
        arguments = listOf(
            navArgument("id") {
                type = NavType.IntType
                nullable = false
            }
        )
    ) { backStackEntry ->
        val id = backStackEntry.arguments?.getInt("id")
            ?: throw IllegalStateException("id was null")

        foodDetailsScreen(
            navController = navController,
            id
        )
    }

    composable(
        route = AppScreens.FoodList.route,
        arguments = listOf(
            navArgument("category") {
                type = NavType.StringType
                nullable = false
            },
            navArgument("appBar") {
                type = NavType.StringType
                nullable = false
            }
        )
    ) { backStackEntry ->
        val category = backStackEntry.arguments?.getString("category")
            ?: throw IllegalStateException("category was null")

        val appBar = backStackEntry.arguments?.getString("appBar")
            ?: throw IllegalStateException("appbar was null")

        foodListScreen(
            navController,
            category,
            appBar
        )
    }
}
