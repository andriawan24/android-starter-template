package com.andriawan.template.ui.pages.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val gameList = viewModel.gameList.value

    if (!gameList.list.isNullOrEmpty()) {
        Column {
            Text(text = "Game List Are")
            LazyColumn {
                items(
                    items = gameList.list,
                    key = { game ->
                        game.id!!
                    }
                ) { game ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp)
                    ) {
                        Text(text = game.name.toString())
                    }
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
