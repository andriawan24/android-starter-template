package com.andriawan.data.network.models

import com.andriawan.common.ext.orZero
import com.andriawan.domain.models.TagModel
import com.google.gson.annotations.SerializedName

data class TagDTO(
    @SerializedName("games_count")
    val gamesCount: Int? = null,
    val id: Int? = null,
    @SerializedName("image_background")
    val imageBackground: String? = null,
    val language: String? = null,
    val name: String? = null,
    val slug: String? = null
) {
    companion object {
        private fun toModel(tag: TagDTO?): TagModel {
            return TagModel(
                id = tag?.id.orZero(),
                gamesCount = tag?.gamesCount.orZero(),
                imageBackground = tag?.imageBackground.orEmpty(),
                language = tag?.language.orEmpty(),
                name = tag?.name.orEmpty(),
                slug = tag?.slug.orEmpty()
            )
        }

        fun toModel(tags: List<TagDTO?>?): List<TagModel> {
            return tags?.map { toModel(it) }.orEmpty()
        }
    }
}
