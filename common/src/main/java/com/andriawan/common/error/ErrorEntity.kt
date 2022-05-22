package com.andriawan.common.error

import androidx.annotation.Keep

@Keep
sealed class ErrorEntity {
    abstract val originalException: Throwable
    data class NetworkException(override val originalException: Throwable): ErrorEntity()
    data class ApiException(override val originalException: Throwable): ErrorEntity()
    data class UnknownException(override val originalException: Throwable): ErrorEntity()
}
