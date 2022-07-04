package com.andriawan.template.ui.pages.favorites

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.andriawan.common.navigation.Routes
import com.andriawan.common.navigation.navigateWithParam
import com.andriawan.common_ui.TemplateTheme
import com.andriawan.domain.models.Games
import com.andriawan.template.ui.components.GameList
import com.andriawan.template.ui.components.GamesShimmer
import com.andriawan.template.ui.pages.home.MainHomeScreen

@ExperimentalFoundationApi
@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel(),
    navHostController: NavHostController,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    val state = viewModel.state

    DisposableEffect(lifecycleOwner) {
        val lifecycle = lifecycleOwner.lifecycle
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.initData()
            }
        }

        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    if (state.errMessage == null) {
        MainFavoriteScreen(
            navHostController = navHostController,
            items = state.items,
            isLoading = state.isLoading
        )
    } else {
        Text(text = "${state.errMessage}")
    }
}

@ExperimentalFoundationApi
@Composable
fun MainFavoriteScreen(
    isLoading: Boolean,
    items: List<Games>?,
    navHostController: NavHostController
) {
    Column {
        if (items.isNullOrEmpty() && isLoading) {
            GamesShimmer()
        } else {
            items?.let { gamesNotNull ->
                GameList(
                    games = gamesNotNull,
                    onGameClicked = { game ->
                        navHostController.navigateWithParam(
                            route = Routes.DETAIL_PAGE,
                            game.id.toString()
                        )
                    }
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun FavoritePagePreview() {
    TemplateTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            FavoriteScreen(
                navHostController = rememberNavController()
            )
        }
    }
}