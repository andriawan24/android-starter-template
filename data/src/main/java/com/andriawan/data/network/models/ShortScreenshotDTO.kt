package com.andriawan.data.network.models

import com.andriawan.common.ext.orZero
import com.andriawan.domain.models.ShortScreenshotModel

data class ShortScreenshotDTO(
    val id: Int? = null,
    val image: String? = null
) {
    companion object {
        private fun toModel(shortScreenshotDTO: ShortScreenshotDTO?) : ShortScreenshotModel {
            return ShortScreenshotModel(
                id = shortScreenshotDTO?.id.orZero(),
                image = shortScreenshotDTO?.image.orEmpty()
            )
        }

        fun toModel(shortScreenshots: List<ShortScreenshotDTO?>?): List<ShortScreenshotModel> {
            return shortScreenshots?.map { toModel(it) }.orEmpty()
        }
    }
}
