package com.andriawan.template.ui.pages.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriawan.common.Resource
import com.andriawan.domain.use_cases.GetGameParam
import com.andriawan.domain.use_cases.GetGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getGameUseCase: GetGameUseCase
): ViewModel() {

    private val _detailState = mutableStateOf(DetailState())
    val detailState: State<DetailState> = _detailState

    fun getGame(id: String) {
        viewModelScope.launch {
            val param = GetGameParam(
                id = id
            )
            getGameUseCase.execute(param).collectLatest {
                when (it) {
                    Resource.Loading -> {
                        _detailState.value = DetailState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _detailState.value = DetailState(game = it.data)
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
}