package com.andriawan.common

import androidx.annotation.Keep
import com.andriawan.common.error.ErrorEntity

@Keep
sealed class Resource<out T> {
    object Loading: Resource<Nothing>()
    data class Success<T>(val data: T? = null): Resource<T>()
    data class Error(val error: ErrorEntity): Resource<Nothing>()
}
