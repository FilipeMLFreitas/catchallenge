package com.catchallenge.ui.screens.breedlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catchallenge.repository.CatRepository
import com.catchallenge.repository.cache.CatCacheRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedListViewModel @Inject constructor(
    private val catRepository: CatRepository,
    private val catCacheRepository: CatCacheRepository,
) : ViewModel() {
    private val _breedListStateFlow: MutableStateFlow<BreedListState> =
        MutableStateFlow(BreedListState.Loading)
    val breedListStateFlow = _breedListStateFlow.asStateFlow()

    fun getBreeds() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val cachedBreeds = catCacheRepository.getBreeds()

                if (cachedBreeds.isNotEmpty()) {
                    _breedListStateFlow.emit(
                        BreedListState.BreedList(
                            cachedBreeds
                        )
                    )
                    return@launch
                }

                val breedList = catRepository.getBreeds()
                catCacheRepository.insertBreeds(breedList)

                val state =
                    if (breedList.isEmpty()) BreedListState.NoData else BreedListState.BreedList(
                        breedList
                    )
                _breedListStateFlow.emit(state)
            } catch (e: Exception) {
                _breedListStateFlow.emit(BreedListState.Error)
            }
        }
    }
}

