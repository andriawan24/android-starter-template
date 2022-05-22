package com.andriawan.domain.use_cases

import com.andriawan.domain.models.Games
import com.andriawan.domain.utils.FlowableUseCase

interface GetLikedGameUseCase: FlowableUseCase<GetLikedGameParam, Games?>

class GetLikedGameParam(
    val gameId: Int
)