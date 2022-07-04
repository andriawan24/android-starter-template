package com.andriawan.template.ui.pages.favorites

import com.andriawan.domain.models.Games

data class FavoriteState(
    val items: List<Games>? = null,
    val isLoading: Boolean = false,
    val errMessage: String? = null
)
