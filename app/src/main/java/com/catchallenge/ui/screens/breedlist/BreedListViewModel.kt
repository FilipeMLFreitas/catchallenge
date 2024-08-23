package com.catchallenge.ui.screens.breedlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catchallenge.repositories.CatRepository
import com.catchallenge.repositories.cache.CatCacheRepository

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
        //TODO: dispatchers should be injected to make it easier for testing
        viewModelScope.launch(Dispatchers.IO) {
            try {
                //let's see if we have the data cached already
                val cachedBreeds = catCacheRepository.getBreeds()

                if (cachedBreeds.isNotEmpty()) {
                    _breedListStateFlow.emit(
                        BreedListState.BreedList(
                            cachedBreeds
                        )
                    )
                    return@launch
                }

                //data not cached, fetch it from the network
                val breedList = catRepository.getBreeds()

                //clear cached data and insert.
                //a bit of a raw way to sync the cached data, but it works
                //TODO: maybe write the cache in parallel to avoid further delay in presenting the data to the user
                catCacheRepository.removeAllBreeds()
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

