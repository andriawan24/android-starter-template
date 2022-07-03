package com.andriawan.template.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andriawan.common_ui.SubtitleColor
import com.andriawan.common_ui.TemplateTheme

@ExperimentalFoundationApi
@Composable
fun GamesShimmer() {
    val items = (1..10).toList()
    CompositionLocalProvider(LocalOverScrollConfiguration provides null) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            items(
                items = items,
                key = { it },
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .padding(PaddingValues(horizontal = 12.dp))
                        .setShimmer(true)
                ) {
                    Column {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "",
                            style = MaterialTheme.typography.subtitle1.copy(
                                color = MaterialTheme.colors.onBackground
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "",
                            style = MaterialTheme.typography.subtitle2.copy(
                                color = SubtitleColor
                            )
                        )
                    }
                }
            }
        }
    }
}

private fun Modifier.setShimmer(
    isShimmer: Boolean
): Modifier = composed {

    if (isShimmer) {
        val shimmerColorState = listOf(
            Color.LightGray.copy(alpha = 0.9f),
            Color.LightGray.copy(alpha = 0.2f),
            Color.LightGray.copy(alpha = 0.9f)
        )

        val transition = rememberInfiniteTransition()
        val translation by transition.animateFloat(
            initialValue = 0f,
            targetValue = 1000f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1200, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

        val brush = Brush.linearGradient(
            colors = shimmerColorState,
            start = Offset(10f, 10f),
            end = Offset(translation, translation)
        )

        this.background(brush = brush)
    } else {
        this
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun GamesShimmerPreview() {
    TemplateTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
        ) {
            GamesShimmer()
        }
    }
}