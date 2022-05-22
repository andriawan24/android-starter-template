package com.andriawan.data.di

import android.content.Context
import androidx.room.Room
import com.andriawan.common.Constants
import com.andriawan.data.local.TemplateDatabase
import com.andriawan.data.local.dao.GamesDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context
    ): TemplateDatabase {
        return Room.databaseBuilder(
            context,
            TemplateDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesGameDao(database: TemplateDatabase): GamesDAO {
        return database.gamesDao()
    }
}