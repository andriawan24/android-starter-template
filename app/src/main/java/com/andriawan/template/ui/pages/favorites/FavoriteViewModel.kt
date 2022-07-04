package com.andriawan.template.ui.pages.favorites

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriawan.common.Resource
import com.andriawan.domain.use_cases.GetLikedGamesParam
import com.andriawan.domain.use_cases.GetLikedGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getLikedGamesUseCase: GetLikedGamesUseCase
) : ViewModel() {

    var state: FavoriteState by mutableStateOf(FavoriteState())
        private set

    fun initData() {
        viewModelScope.launch {
            val params = GetLikedGamesParam()
            getLikedGamesUseCase.execute(params).collectLatest {
                when (it) {
                    Resource.Loading -> {
                        state = state.copy(
                            isLoading = true
                        )
                    }

                    is Resource.Success -> {
                        state = state.copy(
                            items = it.data,
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        state = state.copy(
                            errMessage = it.error.originalException.localizedMessage,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}