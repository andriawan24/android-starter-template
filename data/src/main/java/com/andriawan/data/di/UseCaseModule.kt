package com.andriawan.data.di

import com.andriawan.common.error.ErrorHandler
import com.andriawan.data.use_cases.*
import com.andriawan.domain.repository.GamesRepository
import com.andriawan.domain.use_cases.*
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
        return GetGamesUseCaseImpl(gamesRepository, errorHandler)
    }

    @Provides
    @Singleton
    fun providesGetGameUseCase(
        gamesRepository: GamesRepository,
        errorHandler: ErrorHandler
    ): GetGameUseCase {
        return GetGameUseCaseImpl(gamesRepository, errorHandler)
    }

    @Provides
    @Singleton
    fun providesAddLikeGameUseCase(
        gamesRepository: GamesRepository,
        errorHandler: ErrorHandler
    ): ToggleLikedGameUseCase {
        return ToggleLikeGameUseCaseImpl(gamesRepository, errorHandler)
    }

    @Provides
    @Singleton
    fun providesGetLikedGameUseCase(
        gamesRepository: GamesRepository,
        errorHandler: ErrorHandler
    ): GetLikedGameUseCase {
        return GetLikedGameUseCaseImpl(gamesRepository, errorHandler)
    }

    @Provides
    @Singleton
    fun providesGetLikedGamesUseCase(
        gamesRepository: GamesRepository,
        errorHandler: ErrorHandler
    ): GetLikedGamesUseCase {
        return GetLikedGamesUseCaseImpl(gamesRepository, errorHandler)
    }
}
