package com.andriawan.data.use_cases

import com.andriawan.common.Resource
import com.andriawan.common.error.ErrorHandler
import com.andriawan.domain.models.Games
import com.andriawan.domain.repository.GamesRepository
import com.andriawan.domain.use_cases.GetLikedGameParam
import com.andriawan.domain.use_cases.GetLikedGameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetLikedGameUseCaseImpl(
    private val gamesRepository: GamesRepository,
    private val errorHandler: ErrorHandler
): GetLikedGameUseCase {

    override fun execute(params: GetLikedGameParam): Flow<Resource<Games?>> = flow {
        emit(Resource.Loading)

        try {
            val game = gamesRepository.getLikedGame(params.gameId)

            emit(Resource.Success(game))
        } catch (e: Exception) {
            emit(Resource.Error(errorHandler.getError(e)))
        }
    }.flowOn(Dispatchers.IO)
}