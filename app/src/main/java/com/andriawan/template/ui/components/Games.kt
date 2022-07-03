package com.andriawan.template.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.andriawan.common.DateUtils
import com.andriawan.common_ui.SubtitleColor
import com.andriawan.common_ui.TemplateTheme
import com.andriawan.domain.models.Games
import timber.log.Timber

@ExperimentalFoundationApi
@Composable
fun GameList(
    games: List<Games>,
    onGameClicked: (game: Games) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement
            .spacedBy(18.dp)
    ) {
        items(
            items = games,
            key = { game -> game.id }
        ) { game ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(242.dp)
                    .padding(PaddingValues(horizontal = 12.dp))
                    .clickable(
                        onClick = { onGameClicked.invoke(game) }
                    )
            ) {
                ImageGame(game = game)
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = game.name ?: "No Name",
                    style = MaterialTheme.typography.subtitle1.copy(
                        color = MaterialTheme.colors.onBackground
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = DateUtils.getYear(game.released ?: ""),
                    style = MaterialTheme.typography.subtitle2.copy(
                        color = SubtitleColor
                    )
                )
            }
        }
    }
}

@Composable
fun ImageGame(game: Games) {
    Card(
        shape = MaterialTheme.shapes.medium
    ) {
        var isLoading by remember {
            mutableStateOf(true)
        }

        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(game.background_image)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                onLoading = { isLoading = true },
                onSuccess = { isLoading = false },
                onError = { isLoading = false }
            )

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun GameListPreview() {
    TemplateTheme {
        val gameList by remember {
            mutableStateOf(
                listOf(
                    Games(
                        id = 1,
                        name = "Game Name"
                    )
                )
            )
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            GameList(games = gameList) {
                Timber.d("${it.name} Clicked")
            }
        }
    }
}
