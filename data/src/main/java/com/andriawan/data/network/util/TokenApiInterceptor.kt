package com.andriawan.data.network.util

import okhttp3.Interceptor
import okhttp3.Response

class TokenApiInterceptor(
    private val tokenApi: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val originalUrl = request.url

        val url = originalUrl.newBuilder()
            .addQueryParameter(KEY_PARAMETERS, tokenApi)
            .build()

        val newReq = request.newBuilder()
            .url(url)
            .build()

        return chain.proceed(newReq)
    }

    companion object {
        private const val KEY_PARAMETERS = "key"
    }
}