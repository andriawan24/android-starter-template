package com.andriawan.template.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.andriawan.common.DateHelper
import com.andriawan.common_ui.SubtitleColor
import com.andriawan.common_ui.TemplateTheme
import com.andriawan.domain.models.GameModel

@Composable
fun GameCard(
    game: GameModel,
    onGameClicked: () -> Unit
) {
    var enable by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(242.dp)
            .padding(PaddingValues(horizontal = 12.dp))
            .clickable(
                onClick = {
                    onGameClicked()
                    enable = false
                },
                enabled = enable
            )
    ) {
        GameImage(game = game)
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = game.name,
            style = MaterialTheme.typography.subtitle1.copy(
                color = MaterialTheme.colors.onBackground
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = DateHelper.getYearFromDateString(game.released).orEmpty(),
            style = MaterialTheme.typography.subtitle2.copy(
                color = SubtitleColor
            )
        )
    }
}

@Composable
fun GameImage(game: GameModel, modifier: Modifier = Modifier) {
    Card(shape = MaterialTheme.shapes.medium, modifier = modifier) {
        var isLoading by remember { mutableStateOf(true) }
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(game.backgroundImage)
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
        val gameList by remember { mutableStateOf(emptyList<GameModel>()) }
        Surface(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            GameCard(
                game = gameList[0],
                onGameClicked = { }
            )
        }
    }
}
