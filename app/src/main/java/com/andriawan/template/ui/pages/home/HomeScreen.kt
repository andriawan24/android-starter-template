package com.andriawan.template.ui.pages.home

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.andriawan.common.navigation.Routes
import com.andriawan.common.navigation.navigateWithParam
import com.andriawan.common_ui.TemplateTheme
import com.andriawan.domain.models.Games
import com.andriawan.template.R
import com.andriawan.template.ui.components.GameList
import com.andriawan.template.ui.components.GamesShimmer
import com.andriawan.template.ui.components.HomeHeader

@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeState = viewModel.homeState

    if (homeState.errorMessage == null) {
        MainHomeScreen(
            navController = navController,
            games = homeState.list,
            isLoading = homeState.isLoading,
            checkLoadMore = { viewModel.checkOverScrolled(it) }
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
    isLoading: Boolean,
    checkLoadMore: (Int) -> Unit
) {
    Column {
        HomeHeader(
            title = stringResource(R.string.header_title),
            imageProfile = "https://dummyimage.com/500x500/000/fff",
            haveNotification = false
        )

        if (games.isNullOrEmpty() && isLoading) {
            GamesShimmer(size = 4)
        } else {
            games?.let {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(18.dp),
                ) {
                    itemsIndexed(
                        items = it,
                        key = { _, game -> game.id }
                    ) { i, game ->
                        checkLoadMore(i)

                        GameList(
                            game = game,
                            onGameClicked = {
                                navController.navigateWithParam(
                                    route = Routes.DETAIL_PAGE,
                                    game.id.toString() // Game ID
                                )
                            }
                        )
                    }

                    item {
                        if (isLoading && it.isNotEmpty()) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    TemplateTheme {
        MainHomeScreen(
            navController = rememberNavController(),
            games = emptyList(),
            isLoading = true,
            checkLoadMore = { }
        )
    }
}