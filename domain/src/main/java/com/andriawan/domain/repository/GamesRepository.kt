package com.andriawan.domain.repository

import com.andriawan.domain.models.Games

interface GamesRepository {
    suspend fun getAllGames(page: Int, ordering: String): List<Games>
    suspend fun getGame(id: String): Games?
    suspend fun getLikedGames(): List<Games>
    suspend fun getLikedGame(gameID: Int): Games?
    suspend fun deleteLikedGame(game: Games): Boolean
    suspend fun addLikeGame(game: Games): Boolean
}
