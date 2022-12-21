package com.andriawan.template.ui.pages.favorites

import com.andriawan.domain.models.GameModel

data class FavoriteState(
    val items: List<GameModel>? = null,
    val isLoading: Boolean = false,
    val errMessage: String? = null
)
