package com.andriawan.template.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.andriawan.common.Args
import com.andriawan.common.Routes
import com.andriawan.template.ui.pages.detail.DetailScreen
import com.andriawan.template.ui.pages.home.HomeScreen

@ExperimentalFoundationApi
@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME_PAGE
    ) {
        composable(route = Routes.HOME_PAGE) {
            HomeScreen(navController = navController)
        }

        composable(
            route = "${Routes.DETAIL_PAGE}/{${Args.GAME_ID}}",
            arguments = listOf(
                navArgument(Args.GAME_ID) {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) {
            DetailScreen(
                navController = navController,
                gameId = it.arguments?.getString(Args.GAME_ID) ?: ""
            )
        }
    }
}