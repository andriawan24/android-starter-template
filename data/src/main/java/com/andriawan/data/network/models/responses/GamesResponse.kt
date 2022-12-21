package com.andriawan.data.network.models.responses

import com.andriawan.data.network.models.GamesDTO
import com.andriawan.domain.models.GameModel

data class GamesResponse(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<GamesDTO>? = null,
) {
    companion object {
        fun toModel(games: List<GamesDTO>?): List<GameModel> {
            return games?.map { GamesDTO.toModel(it) }.orEmpty()
        }
    }
}
