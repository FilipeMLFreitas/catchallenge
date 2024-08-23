package com.catchallenge.ui.screens.breedlist

import com.catchallenge.model.Breed

sealed class BreedListState {
    data object Loading : BreedListState()
    data object NoData : BreedListState()
    data object Error : BreedListState()
    data class BreedList(val breedList: List<com.catchallenge.model.Breed>) : BreedListState()
}