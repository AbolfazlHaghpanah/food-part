package com.example.foodpart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.illegalDecoyCallException
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.foodpart.core.AppScreens
import com.example.foodpart.core.BottomNavigationItems
import com.example.foodpart.core.bottomNavItems
import com.example.foodpart.core.foodPartBottomNavigation
import com.example.foodpart.fooddata.FoodData
import com.example.foodpart.fooddata.foodList
import com.example.foodpart.ui.screens.category.CategoryScreenViewModel
import com.example.foodpart.ui.screens.category.categoryScreen
import com.example.foodpart.ui.screens.fooddetails.foodDetailsScreen
import com.example.foodpart.ui.screens.foodlist.foodListScreen
import com.example.foodpart.ui.screens.login.loginScreen
import com.example.foodpart.ui.screens.profile.profileScreen
import com.example.foodpart.ui.screens.search.SearchViewModel
import com.example.foodpart.ui.screens.search.searchScreen
import com.example.foodpart.ui.screens.whattocook.whatToCookScreen
import com.example.foodpart.ui.signup.SignUpScreen
import com.example.foodpart.ui.theme.FoodPartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodPartTheme {
                val bottomNavState = remember {
                    mutableStateOf(true)
                }
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        if (bottomNavState.value)
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
                            mainNavGraph(navController, bottomNavState)
                        }
                    }
                }
            }
        }
    }
}


private fun NavGraphBuilder.mainNavGraph(
    navController: NavController,
    state: MutableState<Boolean>
) {
    composable(AppScreens.Category.route) {
        state.value = true
        categoryScreen(
            navController = navController,
            viewModel = CategoryScreenViewModel()
        )
    }

    composable(AppScreens.Profile.route) {
        state.value = true
        profileScreen(navController = navController)
    }

    composable(AppScreens.Login.route) {
        state.value = true
        loginScreen(navController = navController)
    }

    composable(AppScreens.Search.route) {
        state.value = true
        searchScreen(
            navController = navController,
            viewModel = SearchViewModel()
        )
    }

    composable(AppScreens.WhatToCook.route) {
        state.value = true
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
        state.value = false
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
        state.value = false
        foodListScreen(
            navController,
            category,
            appBar,
            description
        )

    }

    composable(
        route = AppScreens.SignUp.route
    ) {
        state.value = false
        SignUpScreen(
            navController
        )
    }


}
