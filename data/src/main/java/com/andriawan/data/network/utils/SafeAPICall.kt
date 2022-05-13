package com.andriawan.data.network.utils

import retrofit2.Response
import timber.log.Timber
import java.io.IOException

suspend fun <T: Any> safeApiCall(call: suspend () -> Response<T>): T? {
    val response = call.invoke()
    return try {
        if (response.isSuccessful) {
            response.body()
        } else {
            throw ApiException(response.message())
        }
    } catch (e: IOException) {
        Timber.e(e)
        null
    }
}
