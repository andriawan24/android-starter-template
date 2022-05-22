package com.andriawan.data.use_cases

import com.andriawan.common.Resource
import com.andriawan.common.error.ErrorHandler
import com.andriawan.domain.models.Games
import com.andriawan.domain.repository.GamesRepository
import com.andriawan.domain.use_cases.GetGameParam
import com.andriawan.domain.use_cases.GetGameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetGameUseCaseImpl(
    private val gamesRepository: GamesRepository,
    private val errorHandler: ErrorHandler
): GetGameUseCase {

    override fun execute(params: GetGameParam): Flow<Resource<Games>> = flow {
        emit(Resource.Loading)

        try {
            val game = gamesRepository.getGame(
                key = params.key,
                id = params.id
            )

            emit(Resource.Success(game))
        } catch (e: Exception) {
            emit(Resource.Error(errorHandler.getError(e)))
        }

    }.flowOn(Dispatchers.IO)
}