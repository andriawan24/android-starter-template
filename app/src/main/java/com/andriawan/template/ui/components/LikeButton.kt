package com.andriawan.template.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.andriawan.common_ui.TemplateTheme

@Composable
fun LikeButton(isLiked: Boolean, onLoveClicked: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(onClick = onLoveClicked, modifier = modifier) {
        Icon(
            imageVector = if (isLiked) {
                Icons.Default.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun LikedButtonPreview() {
    TemplateTheme {
        LikeButton(isLiked = true, onLoveClicked = {})
    }
}