package com.example.foodpart

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.foodpart.core.AppScreens
import com.example.foodpart.core.SplashScreenViewModel
import com.example.foodpart.ui.screens.category.CategoryScreen
import com.example.foodpart.ui.screens.fooddetails.FoodDetailsScreen
import com.example.foodpart.ui.screens.foodlist.FoodListScreen
import com.example.foodpart.ui.screens.login.LoginScreen
import com.example.foodpart.ui.screens.profile.ProfileScreen
import com.example.foodpart.ui.screens.search.SearchScreen
import com.example.foodpart.ui.screens.search.SearchViewModel
import com.example.foodpart.ui.screens.signup.SignUpScreen
import com.example.foodpart.ui.screens.whattocook.WhatToCookScreen
import com.example.foodpart.ui.theme.FoodPartTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
@Suppress("DEPRECATION")
class MainActivity : ComponentActivity() {
    private val viewModel: SplashScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition { viewModel.isLoading.value }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        setContent {
            FoodPartTheme {
                val navController = rememberNavController()
                Scaffold {
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
    navController: NavController,
) {
    composable(AppScreens.Category.route) {
        CategoryScreen(
            navController = navController
        )
    }

    composable(AppScreens.Profile.route) {
        ProfileScreen(navController = navController)
    }

    composable(AppScreens.Login.route) {
        LoginScreen(navController = navController)
    }

    composable(AppScreens.Search.route) {
        SearchScreen(
            navController = navController,
            viewModel = SearchViewModel()
        )
    }

    composable(AppScreens.WhatToCook.route) {
        WhatToCookScreen(navController = navController)
    }

    composable(
        route = AppScreens.FoodDetails.route,
    ) {
        FoodDetailsScreen(navController = navController)

    }

    composable(
        route = AppScreens.FoodList.route,
        arguments = listOf(
            navArgument("category") {
                type = NavType.StringType
                nullable = false
            },
            navArgument("appbar") {
                type = NavType.StringType
                nullable = false
            },
            navArgument("description") {
                type = NavType.StringType
                nullable = true
            }
        )
    ) { backStackEntry ->
        val category = backStackEntry.arguments?.getString("category")
            ?: throw IllegalStateException("category was null")

        val appBar = backStackEntry.arguments?.getString("appbar")
            ?: throw IllegalStateException("appbar was null")

        val description = backStackEntry.arguments?.getString("description")
        FoodListScreen(
            navController,
            category,
            appBar,
            description
        )
    }

    composable(
        route = AppScreens.SignUp.route
    ) {
        SignUpScreen(
            navController
        )
    }
}
