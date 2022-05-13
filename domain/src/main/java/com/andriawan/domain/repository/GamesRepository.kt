package com.andriawan.domain.repository

import com.andriawan.domain.models.Games

interface GamesRepository {
    suspend fun getAllGames(key: String, page: Int, ordering: String): List<Games>
}
