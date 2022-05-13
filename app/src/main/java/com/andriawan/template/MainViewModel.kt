package com.andriawan.template

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andriawan.domain.models.Games
import com.andriawan.domain.repository.GamesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val gamesRepository: GamesRepository
): ViewModel() {

    private val _gameList = MutableLiveData<List<Games>>()
    val gameList: LiveData<List<Games>> = _gameList

    fun getData() {
        viewModelScope.launch {
            val list = gamesRepository.getAllGames(
                key = "9ffc4221551641a6a54f5e2cedba5994",
                page  = 1,
                ordering = "-rating"
            )

            _gameList.value = list
            Timber.d("List is $list")
        }
    }
}