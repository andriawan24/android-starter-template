package com.andriawan.data.network.models

import com.andriawan.common.ext.orZero
import com.andriawan.domain.models.GenreModel
import com.google.gson.annotations.SerializedName

data class GenreDTO(
    val id: Int? = null,
    @SerializedName("games_count")
    val gamesCount: Int? = null,
    @SerializedName("image_background")
    val backgroundImage: String? = null,
    val name: String? = null,
    val slug: String? = null
) {
    companion object {
        fun toModel(genreDTOs: List<GenreDTO?>?): List<GenreModel> {
            return genreDTOs?.map { toModel(it) }.orEmpty()
        }

        private fun toModel(genreDTO: GenreDTO?): GenreModel {
            return GenreModel(
                id = genreDTO?.id.orZero(),
                gamesCount = genreDTO?.gamesCount.orZero(),
                backgroundImage = genreDTO?.backgroundImage.orEmpty(),
                name = genreDTO?.name.orEmpty(),
                slug = genreDTO?.slug.orEmpty()
            )
        }
    }
}
