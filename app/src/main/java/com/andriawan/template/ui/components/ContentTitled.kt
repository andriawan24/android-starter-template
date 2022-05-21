package com.andriawan.template.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.andriawan.common_ui.SubtitleColor

@Composable
fun ContentTitled(
    modifier: Modifier = Modifier,
    title: String,
    textPadding: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            modifier = Modifier
                .padding(PaddingValues(horizontal = textPadding)),
            style = MaterialTheme.typography.body1.copy(
                color = SubtitleColor,
                fontWeight = FontWeight.SemiBold
            )
        )
        content()
    }
}

@Preview
@Composable
fun ContentTitledPreview() {
    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        ContentTitled(
            title = "This is title"
        ) {
            Text(text = "This is body")
        }
    }
}
