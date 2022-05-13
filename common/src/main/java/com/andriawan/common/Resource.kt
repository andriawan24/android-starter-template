package com.andriawan.common

sealed class Resource<T>(
    data: T? = null,
    message: String? = null
) {

    class Success<T>(data: T?): Resource<T>(data, null)
    class Loading: Resource<None>()
    class Error(message: String?): Resource<None>(message = message)
}
