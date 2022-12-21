package com.andriawan.domain.use_cases

import com.andriawan.common.None
import com.andriawan.common.Resource
import com.andriawan.common.error.ErrorHandler
import com.andriawan.domain.models.GameModel
import com.andriawan.domain.repository.GamesRepository
import com.andriawan.domain.utils.FlowUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetLikedGamesUseCase(
    private val gamesRepository: GamesRepository,
    private val errorHandler: ErrorHandler
) : FlowUseCase<GetLikedGamesUseCase.Param, List<GameModel>> {

    override fun execute(params: Param): Flow<Resource<List<GameModel>>> = flow {
        emit(Resource.Loading)

        try {
            val games = gamesRepository.getLikedGames()
            emit(Resource.Success(games))
        } catch (e: Exception) {
            emit(Resource.Error(errorHandler.getError(e)))
        }
    }.flowOn(Dispatchers.IO)

    object Param
}