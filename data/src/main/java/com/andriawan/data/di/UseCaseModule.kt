package com.andriawan.data.di

import com.andriawan.common.error.ErrorHandler
import com.andriawan.domain.repository.GamesRepository
import com.andriawan.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesGetGamesUseCase(
        gamesRepository: GamesRepository,
        errorHandler: ErrorHandler
    ): GetGamesUseCase {
        return GetGamesUseCase(gamesRepository, errorHandler)
    }

    @Provides
    @Singleton
    fun providesGetGameUseCase(
        gamesRepository: GamesRepository,
        errorHandler: ErrorHandler
    ): GetGameUseCase = GetGameUseCase(gamesRepository, errorHandler)

    @Provides
    @Singleton
    fun providesAddLikeGameUseCase(
        gamesRepository: GamesRepository,
        errorHandler: ErrorHandler
    ): ToggleLikedGameUseCase = ToggleLikedGameUseCase(gamesRepository, errorHandler)

    @Provides
    @Singleton
    fun providesGetLikedGameUseCase(
        gamesRepository: GamesRepository,
        errorHandler: ErrorHandler
    ): GetLikedGameUseCase = GetLikedGameUseCase(gamesRepository, errorHandler)

    @Provides
    @Singleton
    fun providesGetLikedGamesUseCase(
        gamesRepository: GamesRepository,
        errorHandler: ErrorHandler
    ): GetLikedGamesUseCase = GetLikedGamesUseCase(gamesRepository, errorHandler)
}
