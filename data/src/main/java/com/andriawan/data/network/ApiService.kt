package com.andriawan.data.network

import com.andriawan.data.network.models.GamesDTO
import com.andriawan.data.network.models.responses.GamesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("games")
    suspend fun getGames(
        @Query("key") key: String,
        @Query("page") page: Int,
        @Query("ordering") ordering: String
    ): Response<GamesResponse>

    @GET("games/{id}")
    suspend fun getGame(
        @Path("id") id: String,
        @Query("key") key: String
    ): Response<GamesDTO>
}
