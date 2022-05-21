package com.andriawan.common.error

import timber.log.Timber
import java.io.IOException
import java.lang.Exception
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class ErrorHandleImpl: ErrorHandler {
    override fun getError(e: Exception): ErrorEntity {
        return when (e) {
            is ApiException -> {
                ErrorEntity.ApiException(e)
            }

            is TimeoutException -> {
                ErrorEntity.NetworkException(
                    Throwable(
                        message = "Internet is slow"
                    )
                )
            }

            is UnknownHostException -> {
                ErrorEntity.NetworkException(
                    Throwable(
                        message = "No internet"
                    )
                )
            }

            else -> {
                Timber.e(e)
                ErrorEntity.UnknownException(
                    Throwable(
                        message = "Unknown error"
                    )
                )
            }
        }
    }
}
