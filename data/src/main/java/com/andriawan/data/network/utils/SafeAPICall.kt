package com.andriawan.data.network.utils

import retrofit2.Response
import timber.log.Timber
import java.io.IOException

suspend fun <T: Any> safeApiCall(call: suspend () -> Response<T>): T? {
    val response = call.invoke()
    try {
        if (response.isSuccessful) {
            return response.body()
        } else {
            throw ApiException(response.message())
        }
    } catch (e: IOException) {
        Timber.e(e)
        throw NetworkException(e.message.toString())
    } catch (e: Exception) {
        Timber.e(e)
        throw ApiException(e.message.toString())
    }
}
