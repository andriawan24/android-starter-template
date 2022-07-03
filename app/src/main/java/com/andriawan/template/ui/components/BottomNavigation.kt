package com.andriawan.template.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.andriawan.common.navigation.BottomNavDestination
import com.andriawan.common_ui.CardColor
import com.andriawan.common_ui.UnselectedBottomNavColor

@Composable
fun BottomNav(navHostController: NavHostController) {
    val screens = remember { BottomNavDestination.values().toList() }
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = CardColor
    ) {
        screens.forEach { screen ->
            AddBottomNav(
                screen = screen,
                navController = navHostController,
                currentDestination = currentDestination
            )
//            if (screen == BottomNavDestination.BLANK_PAGE) {
//                BottomNavigationItem(
//                    icon = { },
//                    label = { },
//                    selected = false,
//                    onClick = { },
//                    enabled = false
//                )
//            } else {
//
//            }
        }
    }
}

@Composable
fun RowScope.AddBottomNav(
    screen: BottomNavDestination,
    currentDestination: NavDestination?,
    navController: NavController
) {
    BottomNavigationItem(
        label = {
            screen.routeName
        },
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = screen.routeName
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.routeName
        } == true,
        selectedContentColor = Color.White,
        unselectedContentColor = UnselectedBottomNavColor,
        onClick = {
            navController.navigate(screen.routeName) {
                launchSingleTop = true
                popUpTo(
                    navController.graph.findStartDestination().id
                )
            }
        }
    )
}