package com.andriawan.data.network.util

import com.andriawan.common.error.ApiException
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException

fun <T> safeApiRequest(response: Response<T>): T? {
    try {
        if (response.isSuccessful) {
            return response.body()
        } else {
            throw ApiException(
                response.errorBody()?.string()?.let {
                    JSONObject(it).getString("error")
                } ?: response.message()
            )
        }
    } catch (e: Exception) {
        throw e
    }
}