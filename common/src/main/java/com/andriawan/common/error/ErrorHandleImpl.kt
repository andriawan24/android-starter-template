package com.andriawan.common.error

import android.content.Context
import com.andriawan.common.R
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import java.io.IOException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class ErrorHandleImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ErrorHandler {

    override fun getError(e: Exception): String {
        return when (e) {
            is IOException -> {
                "Internet Error"
            }
            is TimeoutException -> {
                "Internet is slow"
            }
            else -> {
                Timber.d(e.message.toString())
                context.getString(R.string.common_unknown_error)
            }
        }
    }
}
