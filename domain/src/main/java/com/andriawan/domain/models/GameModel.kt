package com.andriawan.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class GameModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val added: Int,
    val backgroundImage: String,
    val communityRating: Int,
    val dominantColor: String,
    val genres: List<GenreModel>,
    val name: String,
    val playtime: Int,
    val rating: Double,
    val ratingTop: Int,
    val ratings: List<RatingModel>,
    val ratingsCount: Int,
    val released: String,
    val reviewsCount: Int,
    val reviewsTextCount: Int,
    val shortScreenshots: List<ShortScreenshotModel>,
    val slug: String,
    val tags: List<TagModel>,
    val tba: Boolean,
    val updated: String,
    val description: String,
    val descriptionRaw: String,
    @SerializedName("saturated_color")
    val saturatedColor: String
)
