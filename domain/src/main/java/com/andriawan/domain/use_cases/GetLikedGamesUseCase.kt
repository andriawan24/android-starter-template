package com.andriawan.domain.use_cases

import com.andriawan.domain.models.Games
import com.andriawan.domain.utils.FlowableUseCase

interface GetLikedGamesUseCase: FlowableUseCase<GetLikedGamesParam, List<Games>>

class GetLikedGamesParam()