package com.andriawan.template.ui.pages.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.andriawan.common.navigation.Routes
import com.andriawan.common.navigation.navigateWithParam
import com.andriawan.common_ui.TemplateTheme
import com.andriawan.domain.models.GameModel
import com.andriawan.template.ui.components.GameCard
import com.andriawan.template.ui.components.GamesShimmer

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

    if (state.errorMessage == null) {
        MainFavoriteScreen(
            navHostController = navHostController,
            items = state.items,
            isLoading = state.isLoading
        )
    } else {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = state.errorMessage,
                style = MaterialTheme.typography.h6
            )
        }
    }
}

@Composable
fun MainFavoriteScreen(
    isLoading: Boolean,
    items: List<GameModel>?,
    navHostController: NavHostController
) {
    Column {
        when {
            isLoading -> {
                GamesShimmer()
            }

            items.isNullOrEmpty() -> {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Data tidak tersedia",
                        style = MaterialTheme.typography.h6
                    )
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(18.dp),
                    contentPadding = PaddingValues(top = 12.dp)
                ) {
                    items(
                        items = items,
                        key = { game -> game.id }
                    ) { game ->
                        GameCard(
                            game = game,
                            onGameClicked = {
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
    }
}

@Preview
@Composable
fun FavoritePagePreview() {
    TemplateTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            FavoriteScreen(navHostController = rememberNavController())
        }
    }
}