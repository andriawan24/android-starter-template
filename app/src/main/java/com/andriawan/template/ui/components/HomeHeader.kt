package com.andriawan.template.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.andriawan.common_ui.TemplateTheme
import com.andriawan.template.R

@Composable
fun HomeHeader(
    title: String,
    imageProfile: String,
    haveNotification: Boolean
) {
    var isLoadingImage by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h5.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .weight(1F)
        )
        Spacer(modifier = Modifier.width(24.dp))
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageProfile)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                onLoading = {
                    isLoadingImage = true
                },
                onSuccess = {
                    isLoadingImage = false
                },
                onError = {
                    isLoadingImage = false
                }
            )

            if (isLoadingImage) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            if (haveNotification) {
                Badge(
                    backgroundColor = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(2.dp)
                )
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeHeaderPreviewNightMode() {
    TemplateTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
        ) {
            HomeHeader(
                title = stringResource(id = R.string.header_title),
                imageProfile = "https://dummyimage.com/500x500/000/fff",
                haveNotification = false
            )
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun HomeHeaderPreview() {
    TemplateTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
        ) {
            HomeHeader(
                title = stringResource(id = R.string.header_title),
                imageProfile = "https://dummyimage.com/500x500/000/fff",
                haveNotification = false
            )
        }
    }
}
