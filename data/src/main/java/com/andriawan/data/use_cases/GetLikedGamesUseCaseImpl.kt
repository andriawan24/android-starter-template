package com.andriawan.data.use_cases

import com.andriawan.common.Resource
import com.andriawan.common.error.ErrorHandler
import com.andriawan.domain.models.Games
import com.andriawan.domain.repository.GamesRepository
import com.andriawan.domain.use_cases.GetLikedGamesParam
import com.andriawan.domain.use_cases.GetLikedGamesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetLikedGamesUseCaseImpl(
    private val gamesRepository: GamesRepository,
    private val errorHandler: ErrorHandler
): GetLikedGamesUseCase {

    override fun execute(params: GetLikedGamesParam): Flow<Resource<List<Games>>> = flow {
        emit(Resource.Loading)

        try {
            val games = gamesRepository.getLikedGames()
            emit(Resource.Success(games))
        } catch (e: Exception) {
            emit(Resource.Error(errorHandler.getError(e)))
        }
    }.flowOn(Dispatchers.IO)
}