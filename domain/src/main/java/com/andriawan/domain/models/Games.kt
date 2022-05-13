package com.andriawan.domain.models

data class Games(
    val added: Int?,
    val background_image: String?,
    val community_rating: Int?,
    val dominant_color: String?,
    val genres: List<Genre?>?,
    val id: Int?,
    val name: String?,
    val playtime: Int?,
    val rating: Double?,
    val rating_top: Int?,
    val ratings: List<Rating>?,
    val ratings_count: Int?,
    val released: String?,
    val reviews_count: Int?,
    val reviews_text_count: Int?,
    val short_screenshots: List<ShortScreenshot?>?,
    val slug: String?,
    val tags: List<Tag?>?,
    val tba: Boolean?,
    val updated: String?,
)