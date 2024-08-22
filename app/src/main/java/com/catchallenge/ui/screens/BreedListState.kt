package com.catchallenge.ui.screens

import com.catchallenge.model.Breed

sealed class BreedListState {
    data object Loading : BreedListState()
    data object NoData : BreedListState()
    data object Error : BreedListState()
    data class BreedList(val breedList: List<Breed>) : BreedListState()
}