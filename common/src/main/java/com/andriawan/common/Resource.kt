package com.andriawan.common

import androidx.annotation.Keep

@Keep
sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Success<T>(val data: T? = null) : Resource<T>()
    data class Error(val errorMessage: String) : Resource<Nothing>()
}
