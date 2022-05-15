package com.andriawan.data.repository

import com.andriawan.data.network.ApiService
import com.andriawan.data.network.mappers.toDomain
import com.andriawan.domain.models.Games
import com.andriawan.domain.repository.GamesRepository
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : GamesRepository {

    // TODO: ADD ERROR HANDLER FOR API
    override suspend fun getAllGames(key: String, page: Int, ordering: String): List<Games> {
        val response = apiService.getGames(key, page, ordering)
        return response.body()?.toDomain() ?: emptyList()
    }
}
