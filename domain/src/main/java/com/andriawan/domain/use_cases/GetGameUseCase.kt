package com.andriawan.domain.use_cases

import com.andriawan.domain.models.Games
import com.andriawan.domain.utils.FlowableUseCase

interface GetGameUseCase: FlowableUseCase<GetGameParam, Games>

data class GetGameParam(
    val key: String = "9ffc4221551641a6a54f5e2cedba5994",
    val id: String
)