package com.andriawan.template.ui.pages.favorites

import com.andriawan.domain.models.GameModel

data class FavoriteUiState(
    val items: List<GameModel>? = null,
    val isLoading: Boolean = true,
    val errMessage: String? = null
)
