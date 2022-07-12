package com.andriawan.template.ui.pages.home

import com.andriawan.domain.models.Games

data class HomeState(
    val list: List<Games>? = null,
    var isLoading: Boolean = false,
    var errorMessage: String? = null,
    val currentPage: Int = 1,
    val endReach: Boolean = false
)
