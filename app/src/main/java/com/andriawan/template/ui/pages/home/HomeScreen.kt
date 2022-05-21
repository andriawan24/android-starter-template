package com.andriawan.template.ui.pages.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.andriawan.template.R
import com.andriawan.template.ui.components.*

@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val gameList = viewModel.gameList.value
    val categoryList by remember { mutableStateOf(getCategories()) }

    if (!gameList.list.isNullOrEmpty()) {
        Column {
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
                )
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
