package com.andriawan.domain.models

data class Games(
    val added: Int? = null,
    val background_image: String? = null,
    val community_rating: Int? = null,
    val dominant_color: String? = null,
    val genres: List<Genre?>? = null,
    val id: Int,
    val name: String? = null,
    val playtime: Int? = null,
    val rating: Double? = null,
    val rating_top: Int? = null,
    val ratings: List<Rating>? = null,
    val ratings_count: Int? = null,
    val released: String? = null,
    val reviews_count: Int? = null,
    val reviews_text_count: Int? = null,
    val short_screenshots: List<ShortScreenshot?>? = null,
    val slug: String? = null,
    val tags: List<Tag?>? = null,
    val tba: Boolean? = null,
    val updated: String? = null,

    // Detail game
    val description: String? = null,
    val description_raw: String? = null,
    val saturated_color: String? = null,
)
