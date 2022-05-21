package com.andriawan.template.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andriawan.common_ui.CardColor
import com.andriawan.common_ui.CardStrokeColor
import com.andriawan.common_ui.TemplateTheme
import com.andriawan.template.R

@ExperimentalFoundationApi
@Composable
fun CategoryCardList(categories: List<Category>) {
    CompositionLocalProvider(LocalOverScrollConfiguration provides null) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues(vertical = 12.dp))
        ) {
            itemsIndexed(
                items = categories,
                key = { _, category ->
                    category.id
                }
            ) { index, category ->
                Spacer(modifier = Modifier.width(18.dp))
                CategoryCard(category = category)
                if (index == categories.lastIndex) {
                    Spacer(modifier = Modifier.width(18.dp))
                }
            }
        }
    }
}

@Composable
fun CategoryCard(
    category: Category
) {
    Card(
        backgroundColor = CardColor,
        border = BorderStroke(
            width = 1.dp,
            color = CardStrokeColor
        ),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .width(100.dp)
            .height(120.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                painter = painterResource(id = category.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = category.name,
                textAlign = TextAlign.Center
            )
        }
    }
}

data class Category(
    val id: Int,
    val name: String,
    val image: Int
)

fun getCategories(): List<Category> {
    return listOf(
        Category(
            id = 1,
            name = "Sword Art",
            image = R.drawable.ic_swords
        ),
        Category(
            id = 2,
            name = "Shooter",
            image = R.drawable.ic_shooter
        ),
        Category(
            id = 3,
            name = "Strategy",
            image = R.drawable.ic_cards
        ),
        Category(
            id = 4,
            name = "Shooter",
            image = R.drawable.ic_shooter
        ),
        Category(
            id = 5,
            name = "Strategy",
            image = R.drawable.ic_cards
        )
    )
}

@ExperimentalFoundationApi
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CardCategoryPreview() {
    TemplateTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colors.background
                )
        ) {
            CategoryCardList(categories = getCategories())
        }
    }
}
