package com.andriawan.data.repository

import com.andriawan.data.network.ApiService
import com.andriawan.data.network.mappers.toDomain
import com.andriawan.data.network.utils.safeApiCall
import com.andriawan.domain.models.Games
import com.andriawan.domain.repository.GamesRepository
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : GamesRepository {

    override suspend fun getAllGames(key: String, page: Int, ordering: String): List<Games> {
        val response = safeApiCall { apiService.getGames(key, page, ordering) }
        return response?.toDomain() ?: emptyList()
    }
}