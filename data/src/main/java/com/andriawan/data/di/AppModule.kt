package com.andriawan.data.di

import com.andriawan.common.error.ErrorHandleImpl
import com.andriawan.common.error.ErrorHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindsErrorHandler(impl: ErrorHandleImpl): ErrorHandler
}