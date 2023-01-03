package com.andriawan.template.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.andriawan.common.navigation.BottomNavDestinations
import com.andriawan.common_ui.CardColor
import com.andriawan.common_ui.DarkCardColor
import com.andriawan.common_ui.UnselectedBottomNavColor

@Composable
fun BottomNav(navHostController: NavHostController) {
    val screens = remember { BottomNavDestinations.values().toList() }
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation(
        backgroundColor = if (isSystemInDarkTheme()) DarkCardColor else CardColor,
        elevation = 0.dp
    ) {
        screens.forEach { screen ->
            AddBottomNav(
                screen = screen,
                navController = navHostController,
                currentDestination = currentDestination
            )
        }
    }
}

@Composable
fun RowScope.AddBottomNav(
    screen: BottomNavDestinations,
    currentDestination: NavDestination?,
    navController: NavController
) {
    val isSelected = currentDestination?.hierarchy?.any {
        it.route == screen.routeName
    } == true

    BottomNavigationItem(
        label = { screen.routeName },
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = screen.routeName,
                tint = if (isSelected) {
                    MaterialTheme.colors.primary
                } else {
                    if (isSystemInDarkTheme()) Color.White else Color.Black
                }
            )
        },
        selected = isSelected,
        selectedContentColor = Color.White,
        unselectedContentColor = UnselectedBottomNavColor,
        onClick = {
            navController.navigate(screen.routeName) {
                launchSingleTop = true
                popUpTo(navController.graph.findStartDestination().id)
            }
        }
    )
}