package com.andriawan.domain.models

data class RatingModel(
    val id: Int,
    val title: String,
    val count: Int,
    val percent: Double
)
