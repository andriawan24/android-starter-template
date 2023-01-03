package com.andriawan.domain.utils

import com.andriawan.common.Resource
import kotlinx.coroutines.flow.Flow

interface FlowUseCase<in Params, out T>: UseCase<Params, Flow<Resource<T>>> {
    override fun execute(params: Params): Flow<Resource<T>>
}
