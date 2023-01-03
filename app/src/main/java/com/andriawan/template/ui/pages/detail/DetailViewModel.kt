package com.andriawan.template.ui.pages.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriawan.common.Resource
import com.andriawan.domain.models.GameModel
import com.andriawan.domain.usecases.*
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

    var uiState by mutableStateOf(DetailState())
        private set

    fun getGame(id: String) {
        viewModelScope.launch {
            val param = GetGameUseCase.Param(id = id)
            getGameUseCase.execute(param).collectLatest {
                when (it) {
                    Resource.Loading -> {
                        uiState = uiState.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        uiState = uiState.copy(game = it.data, isLoading = false)
                        it.data?.id?.let { id -> checkIfGameAlreadyAddedToFavorites(id) }
                    }
                    is Resource.Error -> {
                        uiState = uiState.copy(message = it.errorMessage, isLoading = false)
                    }
                }
            }
        }
    }

    private fun checkIfGameAlreadyAddedToFavorites(gameID: Int) {
        viewModelScope.launch {
            val param = GetLikedGameUseCase.Param(gameId = gameID)
            getLikedGameUseCase.execute(param).collectLatest {
                if (it is Resource.Success) {
                    uiState = uiState.copy(
                        isFavoriteGame = it.data != null
                    )
                } else if (it is Resource.Error) {
                    uiState = uiState.copy(
                        message = it.errorMessage
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
                        uiState = uiState.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        uiState = uiState.copy(
                            isFavoriteGame = it.data ?: false,
                            isLoading = false
                        )
                    }
                    is Resource.Error -> {
                        uiState = uiState.copy(
                            isFavoriteGame = true,
                            isLoading = false,
                            message = it.errorMessage
                        )
                    }
                }
            }
        }
    }
}