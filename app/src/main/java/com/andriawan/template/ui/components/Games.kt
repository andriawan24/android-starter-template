package com.andriawan.template.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.andriawan.common.DateUtils
import com.andriawan.common_ui.SubtitleColor
import com.andriawan.common_ui.TemplateTheme
import com.andriawan.domain.models.Games
import com.andriawan.template.R

@ExperimentalFoundationApi
@Composable
fun GameList(games: List<Games>) {
    CompositionLocalProvider(LocalOverScrollConfiguration provides null) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues(vertical = 12.dp))
        ) {
            itemsIndexed(
                items = games,
                key = { _, game ->
                    game.id
                }
            ) { index, game ->
                Spacer(modifier = Modifier.width(18.dp))
                Column(
                    modifier = Modifier
                        .width(260.dp)
                        .height(242.dp)
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
                if (index == games.lastIndex) {
                    Spacer(modifier = Modifier.width(18.dp))
                }
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
                onLoading = {
                    isLoading = true
                },
                onSuccess = {
                    isLoading = false
                },
                onError = {
                    isLoading = false
                }
            )

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.btn_play),
                    contentDescription = "Play Button Image",
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
            modifier = Modifier.fillMaxWidth()
        ) {
            GameList(games = gameList)
        }
    }
}
