package com.andriawan.data.di

import com.andriawan.common.Constants
import com.andriawan.data.network.ApiService
import com.andriawan.common.error.ErrorHandleImpl
import com.andriawan.common.error.ErrorHandler
import com.andriawan.data.BuildConfig
import com.andriawan.data.network.util.TokenApiInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .writeTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(TokenApiInterceptor(BuildConfig.TOKEN_API))
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(
        retrofit: Retrofit
    ): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesErrorHandler(): ErrorHandler {
        return ErrorHandleImpl()
    }
}
