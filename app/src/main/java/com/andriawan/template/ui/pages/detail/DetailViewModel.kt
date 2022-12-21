package com.andriawan.template.ui.pages.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriawan.common.Resource
import com.andriawan.domain.models.GameModel
import com.andriawan.domain.use_cases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getGameUseCase: GetGameUseCase,
    private val toggleLikedGameUseCase: ToggleLikedGameUseCase,
    private val getLikedGameUseCase: GetLikedGameUseCase
) : ViewModel() {

    private val _detailState = mutableStateOf(DetailState())
    val detailState: State<DetailState> = _detailState

    fun getGame(id: String) {
        viewModelScope.launch {
            val param = GetGameUseCase.Param(id = id)
            getGameUseCase.execute(param).collectLatest {
                when (it) {
                    Resource.Loading -> {
                        _detailState.value = DetailState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _detailState.value = DetailState(game = it.data)
                        it.data?.id?.let { id ->
                            getIsLoved(id)
                        }
                    }

                    is Resource.Error -> {
                        _detailState.value = DetailState(
                            message = it.error.originalException.message
                        )
                    }
                }
            }
        }
    }

    private fun getIsLoved(gameID: Int) {
        viewModelScope.launch {
            val param = GetLikedGameUseCase.Param(
                gameId = gameID
            )

            getLikedGameUseCase.execute(param).collectLatest {
                if (it is Resource.Success) {
                    _detailState.value = detailState.value.copy(
                        isLoved = it.data != null
                    )
                } else if (it is Resource.Error) {
                    _detailState.value = detailState.value.copy(
                        message = it.error.originalException.message.toString()
                    )
                }
            }
        }
    }

    fun setLovedGame(game: GameModel) {
        viewModelScope.launch {
            val param = ToggleLikedGameUseCase.Param(game = game)
            toggleLikedGameUseCase.execute(param).collectLatest {
                when (it) {
                    Resource.Loading -> {
                        _detailState.value = detailState.value.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        _detailState.value =
                            detailState.value.copy(isLoved = it.data ?: false, isLoading = false)
                    }

                    is Resource.Error -> {
                        _detailState.value = detailState.value.copy(
                            isLoved = true,
                            isLoading = false,
                            message = it.error.originalException.message.toString()
                        )
                    }
                }
            }
        }
    }
}