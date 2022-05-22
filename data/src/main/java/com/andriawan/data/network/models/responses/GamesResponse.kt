package com.andriawan.data.network.models.responses

import com.andriawan.data.network.models.GamesDTO

data class GamesResponse(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<GamesDTO>?,
)
