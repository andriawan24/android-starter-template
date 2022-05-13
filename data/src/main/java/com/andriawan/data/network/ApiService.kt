package com.andriawan.data.network

import com.andriawan.data.network.models.GamesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("games")
    suspend fun getGames(
        @Query("key") key: String,
        @Query("page") page: Int,
        @Query("ordering") ordering: String
    ): Response<GamesResponse>
}
