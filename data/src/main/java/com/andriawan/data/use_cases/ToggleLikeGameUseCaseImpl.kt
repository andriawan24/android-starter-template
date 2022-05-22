package com.andriawan.data.use_cases

import com.andriawan.common.Resource
import com.andriawan.common.error.ErrorHandler
import com.andriawan.domain.repository.GamesRepository
import com.andriawan.domain.use_cases.ToggleLikedGameUseCase
import com.andriawan.domain.use_cases.ToggleLikeGameUseCaseParam
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ToggleLikeGameUseCaseImpl(
    private val gamesRepository: GamesRepository,
    private val errorHandler: ErrorHandler
): ToggleLikedGameUseCase {

    override fun execute(params: ToggleLikeGameUseCaseParam): Flow<Resource<Boolean>> = flow {
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
}