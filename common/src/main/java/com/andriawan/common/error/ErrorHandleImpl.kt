package com.andriawan.common.error

import java.io.IOException
import java.lang.Exception

class ErrorHandleImpl: ErrorHandler {
    override fun getError(e: Exception): ErrorEntity {
        return when (e) {
            is IOException -> {
                ErrorEntity.NetworkException(e)
            }

            else -> {
                ErrorEntity.UnknownException(e)
            }
        }
    }
}