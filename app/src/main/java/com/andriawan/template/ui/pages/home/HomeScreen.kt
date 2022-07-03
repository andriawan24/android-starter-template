package com.andriawan.template.ui.pages.home

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.andriawan.common.navigation.Routes
import com.andriawan.common_ui.TemplateTheme
import com.andriawan.domain.models.Games
import com.andriawan.template.R
import com.andriawan.template.ui.components.*
import com.andriawan.common.navigation.navigateWithParam

@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeState = viewModel.homeState.value

    if (homeState.errorMessage == null) {
        MainHomeScreen(
            navController = navController,
            games = homeState.list ,
            isLoading = homeState.isLoading
        )
    } else {
        Text(text = "${homeState.errorMessage}")
    }
}

@ExperimentalFoundationApi
@Composable
fun MainHomeScreen(
    navController: NavHostController,
    games: List<Games>?,
    isLoading: Boolean
) {
    Column {
        HomeHeader(
            title = stringResource(id = R.string.header_title),
            imageProfile = "https://dummyimage.com/500x500/000/fff",
            haveNotification = false
        )

        if (games.isNullOrEmpty() && isLoading) {
            GamesShimmer()
        } else {
            games?.let { gamesNotNull ->
                GameList(games = gamesNotNull) { game ->
                    navController.navigateWithParam(
                        route = Routes.DETAIL_PAGE,
                        game.id.toString() // Game ID
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
@ExperimentalFoundationApi
fun HomeScreenPreview() {
    TemplateTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            MainHomeScreen(
                navController = rememberNavController(),
                games = emptyList(),
                isLoading = true
            )
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
@ExperimentalFoundationApi
fun HomeScreenPreviewNightMode() {
    TemplateTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            MainHomeScreen(
                navController = rememberNavController(),
                games = emptyList(),
                isLoading = true
            )
        }
    }
}