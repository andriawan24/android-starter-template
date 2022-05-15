package com.andriawan.template.ui.pages.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriawan.common.Resource
import com.andriawan.domain.use_cases.GetGameUseCaseParam
import com.andriawan.domain.use_cases.GetGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {

    private val _gameList = mutableStateOf(HomeState())
    val gameList: State<HomeState> = _gameList

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            val param = GetGameUseCaseParam()
            getGamesUseCase.execute(param).collect {
                when (it) {
                    Resource.Loading -> {
                        _gameList.value = HomeState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _gameList.value = HomeState(list = it.data)
                    }

                    is Resource.Error -> {
                        _gameList.value = HomeState(
                            errorMessage = it.message
                        )
                    }
                }
            }
        }
    }
}
