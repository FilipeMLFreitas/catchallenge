package com.catchallenge.ui.home

import com.catchallenge.model.Breed

sealed class HomeState {
    data object Loading : HomeState()
    data object NoData : HomeState()
    data object Error : HomeState()
    data class BreedList(val breedList: List<Breed>) : HomeState()
}