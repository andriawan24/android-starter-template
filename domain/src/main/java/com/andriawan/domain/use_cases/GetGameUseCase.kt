package com.andriawan.domain.use_cases

import com.andriawan.domain.models.Games
import com.andriawan.domain.utils.FlowableUseCase

interface GetGameUseCase: FlowableUseCase<GetGameParam, Games>

data class GetGameParam(
    val id: String
)