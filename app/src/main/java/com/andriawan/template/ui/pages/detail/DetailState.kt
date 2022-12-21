package com.andriawan.template.ui.pages.detail

import com.andriawan.domain.models.GameModel

data class DetailState(
    val isLoading: Boolean = false,
    val game: GameModel? = null,
    val message: String? = null,
    val isLoved: Boolean = false
)