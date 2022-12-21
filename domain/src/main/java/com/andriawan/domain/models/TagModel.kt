package com.andriawan.domain.models

data class TagModel(
    val id: Int,
    val gamesCount: Int,
    val imageBackground: String,
    val language: String,
    val name: String,
    val slug: String
)
