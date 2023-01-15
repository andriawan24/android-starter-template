package com.andriawan.template.ui.pages.home

import com.andriawan.domain.models.GameModel

data class HomeState(
    val list: List<GameModel>? = null,
    var isLoading: Boolean = false,
    var errorMessage: String? = null,
    val currentPage: Int = 1,
    val isBottomReached: Boolean = false
)
