package com.andriawan.common.error

import java.lang.Exception

interface ErrorHandler {
    fun getError(e: Exception): ErrorEntity
}