package com.andriawan.data.use_cases

import com.andriawan.common.Resource
import com.andriawan.common.error.ErrorHandler
import com.andriawan.domain.models.Games
import com.andriawan.domain.repository.GamesRepository
import com.andriawan.domain.use_cases.GetGameUseCaseParam
import com.andriawan.domain.use_cases.GetGamesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class GetGamesUseCaseImpl @Inject constructor(
    private val gamesRepository: GamesRepository,
    private val errorHandler: ErrorHandler
) : GetGamesUseCase {

    override fun execute(params: GetGameUseCaseParam): Flow<Resource<List<Games>>> = flow {
        emit(Resource.Loading)
        try {
            val gameList = gamesRepository.getAllGames(
                key = params.key,
                page = params.page,
                ordering = params.ordering
            )

            emit(Resource.Success(data = gameList))
        } catch (e: Exception) {
            emit(Resource.Error(errorHandler.getError(e)))
        }
    }.flowOn(Dispatchers.IO)
}
