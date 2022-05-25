package com.andriawan.template.ui.pages.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.andriawan.common.Routes
import com.andriawan.common_ui.TemplateTheme
import com.andriawan.domain.models.Games
import com.andriawan.template.R
import com.andriawan.template.ui.components.*
import com.andriawan.template.utils.navigateWithParam

@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeState = viewModel.homeState.value

    if (!homeState.list.isNullOrEmpty()) {
        MainHomeScreen(
            navController = navController,
            games = homeState.list
        )
    }

    if (homeState.isLoading) {
        Text(text = "Loading...")
    }

    if (homeState.errorMessage != null) {
        Text(text = "${homeState.errorMessage}")
    }
}

@ExperimentalFoundationApi
@Composable
fun MainHomeScreen(
    navController: NavHostController,
    games: List<Games>
) {
    val scrollState = rememberScrollState()
    val categoryList by remember { mutableStateOf(getCategories()) }
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        HomeHeader(
            title = stringResource(id = R.string.header_title),
            imageProfile = "https://dummyimage.com/500x500/000/fff",
            haveNotification = false
        )

        ContentTitled(title = "Category", textPadding = 18.dp) {
            CategoryCardList(categories = categoryList)
        }

        ContentTitled(
            title = "Featured Games",
            textPadding = 18.dp,
            modifier = Modifier.padding(PaddingValues(top = 8.dp))
        ) {
            GameList(games = games) {
                navController.navigateWithParam(
                    route = Routes.DETAIL_PAGE,
                    it.id.toString() // Game ID
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
@ExperimentalFoundationApi
fun HomeScreenPreview() {
    TemplateTheme {
        HomeScreen(
            navController = rememberNavController()
        )
    }
}