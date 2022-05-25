package com.andriawan.template.ui.pages.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriawan.common.Resource
import com.andriawan.domain.use_cases.GetGamesParam
import com.andriawan.domain.use_cases.GetGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {

    private val _homeState = mutableStateOf(HomeState())
    val homeState: State<HomeState> = _homeState

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            val param = GetGamesParam()
            getGamesUseCase.execute(param).collectLatest {
                when (it) {
                    Resource.Loading -> {
                        _homeState.value = HomeState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _homeState.value = HomeState(list = it.data)
                    }

                    is Resource.Error -> {
                        _homeState.value = HomeState(
                            errorMessage = it.error.originalException.message
                        )
                    }
                }
            }
        }
    }
}
