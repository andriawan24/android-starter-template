package com.andriawan.template.ui.pages.home

import com.andriawan.domain.models.Games

data class HomeState(
    val list: List<Games>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)