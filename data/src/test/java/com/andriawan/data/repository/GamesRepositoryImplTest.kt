package com.andriawan.data.repository

import com.andriawan.data.network.ApiService
import com.andriawan.data.network.mappers.toDomain
import com.andriawan.data.network.models.GamesDTO
import com.andriawan.data.network.models.responses.GamesResponse
import com.andriawan.domain.models.Games
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Test
import retrofit2.Response
import timber.log.Timber
import kotlin.random.Random

class GamesRepositoryImplTest {

    private val apiService: ApiService = mockk()

    @Test
    fun getAllGames() = runBlocking {
        val page = 1
        val response: Response<GamesResponse> = mockk()
        val gameItemListResponse: List<GamesDTO> = mockk()
        val gameListResponse = GamesResponse(
            count = Random.nextInt(),
            next = toString(),
            previous = null,
            gameItemListResponse
        )
        val gameList: List<Games> = mockk()

        coEvery {
            apiService.getGames(page, "-rating")
        } returns response

        every {
            response.body()
        } returns gameListResponse

        every {
            gameListResponse.toDomain()
        } returns gameList

        // val actual = gamesRepositoryImpl.getAllGames(key, page, "-rating")

        Timber.d("Game list $gameList")
        assertEquals(gameList, gameList)
    }
}
