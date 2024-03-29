package com.andriawan.domain.usecases

import com.andriawan.common.Resource
import com.andriawan.common.error.ErrorHandler
import com.andriawan.domain.models.GameModel
import com.andriawan.domain.repository.GamesRepository
import com.andriawan.domain.utils.FlowUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class GetGamesUseCase(
    private val gamesRepository: GamesRepository,
    private val errorHandler: ErrorHandler
) : FlowUseCase<GetGamesUseCase.Param, List<GameModel>> {

    override fun execute(params: Param): Flow<Resource<List<GameModel>>> = flow {
        emit(Resource.Loading)
        try {
            val gameList = gamesRepository.getAllGames(
                page = params.page,
                ordering = params.ordering
            )

            gameList.forEach {
                val likedGame = gamesRepository.getLikedGame(it.id)
                if (likedGame != null) {
                    gamesRepository.addLikeGame(it)
                }
            }

            emit(Resource.Success(data = gameList))
        } catch (e: Exception) {
            emit(Resource.Error(errorHandler.getError(e)))
        }
    }.flowOn(Dispatchers.IO)

    data class Param(
        val page: Int = 1,
        val ordering: String = "-rating"
    )
}
