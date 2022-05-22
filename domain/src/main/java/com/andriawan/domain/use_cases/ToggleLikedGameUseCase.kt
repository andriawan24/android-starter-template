package com.andriawan.domain.use_cases

import com.andriawan.domain.models.Games
import com.andriawan.domain.utils.FlowableUseCase

interface ToggleLikedGameUseCase: FlowableUseCase<ToggleLikeGameUseCaseParam, Boolean>

data class ToggleLikeGameUseCaseParam(
    val game: Games
)