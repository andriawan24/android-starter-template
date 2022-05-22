package com.andriawan.data.repository

import com.andriawan.data.network.ApiService
import com.andriawan.data.network.mappers.toDomain
import com.andriawan.data.network.util.safeApiRequest
import com.andriawan.domain.models.Games
import com.andriawan.domain.repository.GamesRepository
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : GamesRepository {

    override suspend fun getAllGames(key: String, page: Int, ordering: String): List<Games> {
        val response = safeApiRequest(apiService.getGames(key, page, ordering))
        return response?.toDomain() ?: emptyList()
    }

    override suspend fun getGame(key: String, id: String): Games? {
        val response = safeApiRequest(apiService.getGame(key = key, id = id))
        return response?.toDomain()
    }
}
