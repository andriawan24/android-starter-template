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

class GetGameUseCase(
    private val gamesRepository: GamesRepository,
    private val errorHandler: ErrorHandler
): FlowUseCase<GetGameUseCase.Param, GameModel> {

    override fun execute(params: Param): Flow<Resource<GameModel>> = flow {
        emit(Resource.Loading)
        try {
            val game = gamesRepository.getGame(id = params.id)
            emit(Resource.Success(game))
        } catch (e: Exception) {
            emit(Resource.Error(errorHandler.getError(e)))
        }
    }.flowOn(Dispatchers.IO)

    data class Param(
        val id: String
    )
}