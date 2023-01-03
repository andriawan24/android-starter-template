package com.andriawan.data.network.models

import com.andriawan.common.ext.orZero
import com.andriawan.domain.models.RatingModel

data class RatingDTO(
    val id: Int? = null,
    val title: String? = null,
    val count: Int? = null,
    val percent: Double? = null
) {
    companion object {
        private fun toModel(rating: RatingDTO?) : RatingModel {
            return RatingModel(
                id = rating?.id.orZero(),
                title = rating?.title.orEmpty(),
                count = rating?.count.orZero(),
                percent = rating?.percent.orZero()
            )
        }

        fun toModel(ratings: List<RatingDTO?>?) : List<RatingModel> {
            return ratings?.map { toModel(it) }.orEmpty()
        }
    }
}
