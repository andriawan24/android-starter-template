package com.andriawan.template.ui.pages.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriawan.common.Resource
import com.andriawan.domain.usecases.GetGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {

    var uiState by mutableStateOf(HomeState())
        private set

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            val param = GetGamesUseCase.Param(page = uiState.currentPage)
            getGamesUseCase.execute(param).collectLatest {
                when (it) {
                    Resource.Loading -> {
                        uiState = uiState.copy(
                            isLoading = true
                        )
                    }

                    is Resource.Success -> {
                        uiState = uiState.copy(
                            list = uiState.list.orEmpty() + it.data.orEmpty(),
                            currentPage = uiState.currentPage + 1,
                            isBottomReached = it.data.isNullOrEmpty(),
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        uiState = uiState.copy(
                            errorMessage = it.errorMessage
                        )
                    }
                }
            }
        }
    }

    fun checkBottomScrolled(index: Int) {
        if (index == uiState.list.orEmpty().size - 1 &&
            !uiState.list.isNullOrEmpty() && !uiState.isBottomReached
        ) {
            getData()
        }
    }
}
