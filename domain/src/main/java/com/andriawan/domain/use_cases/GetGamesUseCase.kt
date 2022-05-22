package com.andriawan.domain.use_cases

import com.andriawan.domain.models.Games
import com.andriawan.domain.utils.FlowableUseCase

interface GetGamesUseCase : FlowableUseCase<GetGamesParam, List<Games>>

data class GetGamesParam(
    val key: String = "9ffc4221551641a6a54f5e2cedba5994",
    val page: Int = 1,
    val ordering: String = "-rating"
)
