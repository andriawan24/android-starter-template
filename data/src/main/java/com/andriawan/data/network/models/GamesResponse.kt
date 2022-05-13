package com.andriawan.data.network.models

data class GamesResponse(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<GamesDTO>?,
)
