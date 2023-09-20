package com.example.foodpart.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.foodpart.core.bottomNavItems

@Composable
fun FoodPartBottomNavigation(
    navController: NavController
) {
    BottomNavigation(
        modifier = Modifier
            .clip(
                shape = MaterialTheme
                    .shapes
                    .medium
                    .copy(
                        bottomEnd = CornerSize(0.dp),
                        bottomStart = CornerSize(0.dp)
                    )
            ),
        backgroundColor = MaterialTheme.colors.secondary,

    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        bottomNavItems.forEach { item ->
            BottomNavigationItem(
                modifier = Modifier
                    .fillMaxSize()

                ,
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(
                            navController
                                .graph
                                .findStartDestination().route!!
                        ) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        modifier = Modifier
                            ,
                        painter = painterResource(id = item.icon),
                        contentDescription = item.label,
                    )
                },
                label = {
                    Text(
                        modifier = Modifier
                            .wrapContentWidth(unbounded = true),
                        text = item.label,
                        style = MaterialTheme.typography.subtitle1
                    )
                },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.onBackground,

            )

        }
    }

}