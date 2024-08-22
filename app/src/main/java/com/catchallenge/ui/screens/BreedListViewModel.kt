package com.catchallenge.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catchallenge.repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedListViewModel @Inject constructor(
    private val catRepository: CatRepository,
) : ViewModel() {
    private val _breedListStateFlow: MutableStateFlow<BreedListState> = MutableStateFlow(BreedListState.Loading)
    val breedListStateFlow = _breedListStateFlow.asStateFlow()

    fun getBreeds() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val breedList = catRepository.getBreeds()
                val state =
                    if (breedList.isEmpty()) BreedListState.NoData else BreedListState.BreedList(breedList)
                _breedListStateFlow.emit(state)
            } catch (_: Exception) {
                _breedListStateFlow.emit(BreedListState.Error)
            }
        }
    }
}

