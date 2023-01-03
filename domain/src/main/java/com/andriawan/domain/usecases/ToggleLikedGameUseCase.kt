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

class ToggleLikedGameUseCase(
    private val gamesRepository: GamesRepository,
    private val errorHandler: ErrorHandler
) : FlowUseCase<ToggleLikedGameUseCase.Param, Boolean> {

    override fun execute(params: Param): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        try {
            val game = gamesRepository.getLikedGame(params.game.id)
            if (game == null) {
                val likedGame = gamesRepository.addLikeGame(game = params.game)
                emit(Resource.Success(likedGame))
            } else {
                gamesRepository.deleteLikedGame(game = params.game)
                emit(Resource.Success(false))
            }
        } catch (e: Exception) {
            emit(Resource.Error(errorHandler.getError(e)))
        }
    }.flowOn(Dispatchers.IO)

    data class Param(
        val game: GameModel
    )
}