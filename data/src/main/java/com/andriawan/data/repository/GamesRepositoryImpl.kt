package com.andriawan.data.repository

import com.andriawan.data.local.dao.GamesDAO
import com.andriawan.data.network.ApiService
import com.andriawan.data.network.mappers.toDomain
import com.andriawan.data.network.util.safeApiRequest
import com.andriawan.domain.models.Games
import com.andriawan.domain.repository.GamesRepository
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val gamesDAO: GamesDAO
) : GamesRepository {

    override suspend fun getAllGames(page: Int, ordering: String): List<Games> {
        val response = safeApiRequest(apiService.getGames(page = page, ordering = ordering))
        return response?.toDomain() ?: emptyList()
    }

    override suspend fun getGame(id: String): Games? {
        val response = safeApiRequest(apiService.getGame(id = id))
        return response?.toDomain()
    }

    override suspend fun getLikedGames(): List<Games> {
        return try {
            val games = gamesDAO.getAllGames()
            games
        } catch (e: Exception) {
            Timber.e("Error message ${e.message}")
            emptyList()
        }
    }

    override suspend fun getLikedGame(gameID: Int): Games? {
        return try {
            val games = gamesDAO.getGame(gameID)
            games
        } catch (e: Exception) {
            Timber.e("Error message ${e.message}")
            null
        }
    }

    override suspend fun addLikeGame(game: Games): Boolean {
        return try {
            gamesDAO.insertGame(game)
            true
        } catch (e: Exception) {
            Timber.e("Error message ${e.message}")
            false
        }
    }

    override suspend fun deleteLikedGame(game: Games): Boolean {
        return try {
            gamesDAO.deleteGame(game)
            true
        } catch (e: Exception) {
            Timber.e("Error message ${e.message}")
            false
        }
    }
}
