package com.andriawan.domain.repository

import com.andriawan.domain.models.GameModel

interface GamesRepository {
    suspend fun getAllGames(page: Int, ordering: String): List<GameModel>
    suspend fun getGame(id: String): GameModel
    suspend fun getLikedGames(): List<GameModel>
    suspend fun getLikedGame(gameID: Int): GameModel?
    suspend fun deleteLikedGame(game: GameModel): Boolean
    suspend fun addLikeGame(game: GameModel): Boolean
}
