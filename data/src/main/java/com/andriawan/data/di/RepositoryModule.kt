package com.andriawan.data.di

import com.andriawan.data.local.dao.GamesDAO
import com.andriawan.data.network.ApiService
import com.andriawan.data.repository.GamesRepositoryImpl
import com.andriawan.domain.repository.GamesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesGamesRepository(
        apiService: ApiService,
        gamesDAO: GamesDAO
    ): GamesRepository {
        return GamesRepositoryImpl(apiService, gamesDAO)
    }
}
