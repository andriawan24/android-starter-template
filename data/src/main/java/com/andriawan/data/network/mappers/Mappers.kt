package com.andriawan.data.network.mappers

import com.andriawan.data.network.models.*
import com.andriawan.data.network.models.responses.GamesResponse
import com.andriawan.domain.models.*

fun GamesResponse.toDomain(): List<Games>? {
    return this.results?.map {
        Games(
            added = it.added,
            background_image = it.background_image,
            community_rating = it.community_rating,
            dominant_color = it.dominant_color,
            genres = it.genres?.map { genre -> genre?.toDomain() },
            id = it.id,
            name = it.name,
            playtime = it.playtime,
            rating = it.rating,
            rating_top = it.rating_top,
            ratings = it.ratings?.map { rating -> rating.toDomain() },
            ratings_count = it.ratings_count,
            released = it.released,
            reviews_count = it.reviews_count,
            reviews_text_count = it.reviews_text_count,
            short_screenshots = it.short_screenshots?.map { screenShot -> screenShot?.toDomain() },
            slug = it.slug,
            tags = it.tags?.map { tag -> tag?.toDomain() },
            tba = it.tba,
            updated = it.updated
        )
    }
}

fun GamesDTO.toDomain(): Games {
    return Games(
        added = added,
        background_image = background_image,
        community_rating = community_rating,
        dominant_color = dominant_color,
        genres = genres?.map { genre -> genre?.toDomain() },
        id = id,
        name = name,
        playtime = playtime,
        rating = rating,
        rating_top = rating_top,
        ratings = ratings?.map { rating -> rating.toDomain() },
        ratings_count = ratings_count,
        released = released,
        reviews_count = reviews_count,
        reviews_text_count = reviews_text_count,
        short_screenshots = short_screenshots?.map { screenShot -> screenShot?.toDomain() },
        slug = slug,
        tags = tags?.map { tag -> tag?.toDomain() },
        tba = tba,
        updated = updated,
        description = description,
        description_raw = description_raw,
        saturated_color = saturated_color
    )
}

fun GenreDTO.toDomain(): Genre {
    return Genre(
        games_count,
        id,
        image_background,
        name,
        slug
    )
}

fun RatingDTO.toDomain(): Rating {
    return Rating(
        id,
        title,
        count,
        percent
    )
}

fun ShortScreenshotDTO.toDomain(): ShortScreenshot {
    return ShortScreenshot(
        id,
        image
    )
}

fun TagDTO.toDomain(): Tag {
    return Tag(
        games_count,
        id,
        image_background,
        language,
        name,
        slug
    )
}
