package com.andriawan.domain.utils

interface UseCase<in Params, out T> {
    fun execute(params: Params): T
}