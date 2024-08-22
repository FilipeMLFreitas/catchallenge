package com.catchallenge.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catchallenge.repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val catRepository: CatRepository,
) : ViewModel() {
    private val _homeStateFlow: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Loading)
    val homeStateFlow = _homeStateFlow.asStateFlow()

    fun getBreeds() {
        viewModelScope.launch {
            try {
                val breedList = catRepository.getBreeds()
                val state =
                    if (breedList.isEmpty()) HomeState.NoData else HomeState.BreedList(breedList)
                _homeStateFlow.emit(state)
            } catch (_: Exception) {
                _homeStateFlow.emit(HomeState.Error)
            }
        }
    }
}

