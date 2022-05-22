package com.andriawan.data.di

import com.andriawan.common.error.ErrorHandler
import com.andriawan.data.use_cases.GetGameUseCaseImpl
import com.andriawan.data.use_cases.GetGamesUseCaseImpl
import com.andriawan.data.use_cases.ToggleLikeGameUseCaseImpl
import com.andriawan.data.use_cases.GetLikedGameUseCaseImpl
import com.andriawan.domain.repository.GamesRepository
import com.andriawan.domain.use_cases.ToggleLikedGameUseCase
import com.andriawan.domain.use_cases.GetGameUseCase
import com.andriawan.domain.use_cases.GetGamesUseCase
import com.andriawan.domain.use_cases.GetLikedGameUseCase
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
}
