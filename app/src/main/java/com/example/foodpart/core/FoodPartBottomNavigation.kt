package com.example.foodpart.core

import androidx.compose.foundation.background
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

@Composable
fun foodPartBottomNavigation(
    navController: NavController
) {
    BottomNavigation (
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
                        painter = painterResource(id = item.icon),
                        contentDescription = item.label,
                        tint = if(item.route == currentDestination?.route) MaterialTheme.colors.primary
                        else MaterialTheme.colors.onBackground,
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        style = MaterialTheme.typography.subtitle1,
                        color = if(item.route == currentDestination?.route) MaterialTheme.colors.primary
                        else MaterialTheme.colors.onBackground,
                    )
                }

            )

        }
    }

}