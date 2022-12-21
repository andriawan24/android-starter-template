package com.andriawan.data.repository

import com.andriawan.data.local.dao.GamesDAO
import com.andriawan.data.network.ApiService
import com.andriawan.data.network.models.GamesDTO
import com.andriawan.data.network.models.responses.GamesResponse
import com.andriawan.data.network.util.safeApiRequest
import com.andriawan.domain.models.GameModel
import com.andriawan.domain.repository.GamesRepository
import timber.log.Timber
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val gamesDAO: GamesDAO
) : GamesRepository {

    override suspend fun getAllGames(page: Int, ordering: String): List<GameModel> {
        val response = safeApiRequest(apiService.getGames(page = page, ordering = ordering))
        return GamesResponse.toModel(response?.results)
    }

    override suspend fun getGame(id: String): GameModel {
        val response = safeApiRequest(apiService.getGame(id = id))
        return GamesDTO.toModel(response)
    }

    override suspend fun getLikedGames(): List<GameModel> {
        return try {
            val games = gamesDAO.getAllGames()
            games
        } catch (e: Exception) {
            Timber.e("Error message ${e.message}")
            emptyList()
        }
    }

    override suspend fun getLikedGame(gameID: Int): GameModel? {
        return try {
            val games = gamesDAO.getGame(gameID)
            games
        } catch (e: Exception) {
            Timber.e("Error message ${e.message}")
            null
        }
    }

    override suspend fun addLikeGame(game: GameModel): Boolean {
        return try {
            gamesDAO.insertGame(game)
            true
        } catch (e: Exception) {
            Timber.e("Error message ${e.message}")
            false
        }
    }

    override suspend fun deleteLikedGame(game: GameModel): Boolean {
        return try {
            gamesDAO.deleteGame(game)
            true
        } catch (e: Exception) {
            Timber.e("Error message ${e.message}")
            false
        }
    }
}
