package com.andriawan.template.ui.pages.detail

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.andriawan.common_ui.TemplateTheme
import com.andriawan.domain.models.Games
import com.andriawan.template.ui.components.GameRating
import com.andriawan.template.ui.components.LikedButton

@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel(),
    gameId: String
) {
    val state = viewModel.detailState.value
    LaunchedEffect(Unit) {
        viewModel.getGame(gameId)
    }

    when {
        state.game != null -> {
            MainDetailPage(
                game = state.game,
                isLoved = state.isLoved,
                onBackClicked = {
                    navController.navigateUp()
                },
                onLoveClicked = {
                    viewModel.setLovedGame(it)
                }
            )
        }

        state.isLoading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

        state.message != null -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = state.message)
            }
        }
    }
}

@Composable
fun MainDetailPage(
    game: Games,
    isLoved: Boolean,
    onBackClicked: () -> Unit,
    onLoveClicked: (game: Games) -> Unit
) {
    Column {
        DetailHeader(
            game = game,
            onBackClicked = onBackClicked,
            isLoved = isLoved,
            onLoveClicked = {
                onLoveClicked.invoke(game)
            }
        )
        DetailBody(game = game)
    }
}

@Composable
fun DetailHeader(
    game: Games,
    isLoved: Boolean,
    onBackClicked: () -> Unit,
    onLoveClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BackButton(onBackClicked = onBackClicked)
        Spacer(modifier = Modifier.width(24.dp))
        AppBarTitle(
            name = game.name ?: "",
            rating = game.rating ?: 0.0,
            modifier = Modifier.weight(1F)
        )
        Spacer(modifier = Modifier.width(24.dp))
        LikedButton(
            isLiked = isLoved,
            onLoveClicked = onLoveClicked
        )
    }
}

@Composable
fun BackButton(onBackClicked: () -> Unit) {
    IconButton(
        onClick = { onBackClicked() },
        modifier = Modifier
            .clip(CircleShape)
            .background(Color.White)
            .size(30.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = Color.Black
        )
    }
}

@Composable
fun AppBarTitle(modifier: Modifier = Modifier, name: String, rating: Double) {
    Column(modifier = modifier) {
        Text(
            text = name,
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(2.dp))
        GameRating(rating = rating)
    }
}

@Composable
fun DetailBody(game: Games) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
    ) {
        DetailPoster(image = game.background_image ?: "")
        DescriptionGame(description = game.description_raw.toString())
    }
}

@Composable
fun DetailPoster(image: String) {
    Card(
        modifier = Modifier
            .padding(18.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}

@Composable
fun DescriptionGame(description: String) {
    Column(
        modifier = Modifier
            .padding(PaddingValues(horizontal = 18.dp))
    ) {
        Text(text = "Description")
        Text(text = description)
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DescriptionGamePreview() {
    TemplateTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
        ) {
            DescriptionGame(description = "lorem")
        }
    }
}