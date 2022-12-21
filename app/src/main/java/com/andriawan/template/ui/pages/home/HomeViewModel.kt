package com.andriawan.template.ui.pages.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriawan.common.Resource
import com.andriawan.domain.use_cases.GetGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {

    var homeState by mutableStateOf(HomeState())
        private set

//    private val paginator = DefaultPaginator<Int, Games>(
//        initialKey = homeState.currentPage,
//        onLoadUpdated = {
//            homeState = homeState.copy(
//                isLoading = true
//            )
//        },
//        onRequest = { page ->
//            val param = GetGamesParam(
//                page = page
//            )
//            getGamesUseCase.execute(param)
//        }
//    )

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            val param = GetGamesUseCase.Param(page = homeState.currentPage)
            getGamesUseCase.execute(param).collectLatest {
                when (it) {
                    Resource.Loading -> {
                        homeState = homeState.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        homeState = homeState.copy(
                            list = homeState.list.orEmpty() + it.data.orEmpty(),
                            currentPage = homeState.currentPage + 1,
                            endReach = it.data.isNullOrEmpty(),
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        homeState = homeState.copy(
                            errorMessage = it.error.originalException.message
                        )
                    }
                }
            }
        }
    }

    fun checkOverScrolled(index: Int) {
        if (index == homeState.list.orEmpty().size - 1 && homeState.list.orEmpty()
                .isNotEmpty() && !homeState.endReach
        ) {
            getData()
        }
    }
}
