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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.andriawan.common.Routes
import com.andriawan.template.R
import com.andriawan.template.ui.components.*
import com.andriawan.template.utils.navigateWithParam

@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val gameList = viewModel.gameList.value
    val categoryList by remember { mutableStateOf(getCategories()) }
    val scrollState = rememberScrollState()

    if (!gameList.list.isNullOrEmpty()) {
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
                GameList(
                    games = gameList.list
                ) {
                    navController.navigateWithParam(
                        route = Routes.DETAIL_PAGE,
                        it.id.toString() // Game ID
                    )
                }
            }
        }
    }

    if (gameList.isLoading) {
        Text(text = "Loading...")
    }

    if (gameList.errorMessage != null) {
        Text(text = "${gameList.errorMessage}")
    }
}
