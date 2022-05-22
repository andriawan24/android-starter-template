package com.andriawan.data.use_cases

import com.andriawan.common.Resource
import com.andriawan.common.error.ErrorHandler
import com.andriawan.domain.models.Games
import com.andriawan.domain.repository.GamesRepository
import com.andriawan.domain.use_cases.GetGamesParam
import com.andriawan.domain.use_cases.GetGamesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class GetGamesUseCaseImpl(
    private val gamesRepository: GamesRepository,
    private val errorHandler: ErrorHandler
) : GetGamesUseCase {
    override fun execute(params: GetGamesParam): Flow<Resource<List<Games>>> = flow {
        emit(Resource.Loading)
        try {
            val gameList = gamesRepository.getAllGames(
                page = params.page,
                ordering = params.ordering
            )

            gameList.forEach {
                val isAdded = gamesRepository.getLikedGame(it.id)
                if (isAdded != null) {
                    gamesRepository.addLikeGame(it)
                }
            }

            emit(Resource.Success(data = gameList))
        } catch (e: Exception) {
            emit(Resource.Error(errorHandler.getError(e)))
        }
    }.flowOn(Dispatchers.IO)
}
