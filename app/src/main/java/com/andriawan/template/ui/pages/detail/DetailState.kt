package com.andriawan.template.ui.pages.detail

import com.andriawan.domain.models.Games

data class DetailState(
    val isLoading: Boolean = false,
    val game: Games? = null,
    val message: String? = null,
    val isLoved: Boolean = false
)