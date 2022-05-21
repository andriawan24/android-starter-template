package com.andriawan.data.di

import com.andriawan.common.error.ErrorHandler
import com.andriawan.data.use_cases.GetGamesUseCaseImpl
import com.andriawan.domain.repository.GamesRepository
import com.andriawan.domain.use_cases.GetGamesUseCase
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
}
