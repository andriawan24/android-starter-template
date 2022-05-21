package com.andriawan.domain.utils

interface SuspendUseCase<in Params, out T> {
    suspend fun execute(params: Params): T
}
