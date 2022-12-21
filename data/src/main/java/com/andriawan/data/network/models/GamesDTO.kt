package com.andriawan.data.network.models

import com.andriawan.common.ext.orFalse
import com.andriawan.common.ext.orZero
import com.andriawan.domain.models.GameModel
import com.google.gson.annotations.SerializedName

data class GamesDTO(
    val added: Int? = null,
    @SerializedName("background_image")
    val backgroundImage: String? = null,
    @SerializedName("community_rating")
    val communityRating: Int? = null,
    @SerializedName("dominant_color")
    val dominantColor: String? = null,
    val genres: List<GenreDTO?>? = null,
    val id: Int? = null,
    val name: String? = null,
    val playtime: Int? = null,
    val rating: Double? = null,
    @SerializedName("rating_top")
    val ratingTop: Int? = null,
    val ratings: List<RatingDTO>? = null,
    @SerializedName("ratings_count")
    val ratingsCount: Int? = null,
    val released: String? = null,
    @SerializedName("reviews_count")
    val reviewsCount: Int? = null,
    @SerializedName("reviews_text_count")
    val reviewsTextCount: Int? = null,
    @SerializedName("short_screenshots")
    val shortScreenshots: List<ShortScreenshotDTO?>? = null,
    val slug: String? = null,
    val tags: List<TagDTO?>? = null,
    val tba: Boolean? = null,
    val updated: String? = null,
    val description: String? = null,
    @SerializedName("description_raw")
    val descriptionRaw: String? = null,
    @SerializedName("saturated_color")
    val saturatedColor: String? = null,
) {
    companion object {
        fun toModel(gamesDTO: GamesDTO?): GameModel {
            return GameModel(
                added = gamesDTO?.added.orZero(),
                backgroundImage = gamesDTO?.backgroundImage.orEmpty(),
                communityRating = gamesDTO?.communityRating.orZero(),
                dominantColor = gamesDTO?.dominantColor.orEmpty(),
                genres = GenreDTO.toModel(gamesDTO?.genres),
                id = gamesDTO?.id.orZero(),
                name = gamesDTO?.name.orEmpty(),
                playtime = gamesDTO?.playtime.orZero(),
                rating = gamesDTO?.rating.orZero(),
                ratingTop = gamesDTO?.ratingTop.orZero(),
                ratings = RatingDTO.toModel(gamesDTO?.ratings),
                ratingsCount = gamesDTO?.ratingsCount.orZero(),
                released = gamesDTO?.released.orEmpty(),
                reviewsCount = gamesDTO?.reviewsCount.orZero(),
                reviewsTextCount = gamesDTO?.reviewsTextCount.orZero(),
                shortScreenshots = ShortScreenshotDTO.toModel(gamesDTO?.shortScreenshots),
                slug = gamesDTO?.slug.orEmpty(),
                tags = TagDTO.toModel(gamesDTO?.tags),
                tba = gamesDTO?.tba.orFalse(),
                updated = gamesDTO?.updated.orEmpty(),
                description = gamesDTO?.description.orEmpty(),
                descriptionRaw = gamesDTO?.descriptionRaw.orEmpty(),
                saturatedColor = gamesDTO?.saturatedColor.orEmpty()
            )
        }
    }
}
