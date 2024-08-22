package com.catchallenge.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.catchallenge.ui.elements.UiBreedList
import com.catchallenge.ui.elements.UiError
import com.catchallenge.ui.elements.UiLoading
import com.catchallenge.ui.elements.UiNoData

@Composable
fun BreedListScreen(
    onBreedClick: (String) -> Unit,
) {
    //TODO: using hiltViewModel instead of just viewmodel, the UI shouldn't know what DI is being used
    val breedListViewModel = hiltViewModel<BreedListViewModel>()

    val state by breedListViewModel.breedListStateFlow.collectAsStateWithLifecycle()

    LifecycleStartEffect(true) {
        breedListViewModel.getBreeds()

        onStopOrDispose { }
    }

    when (state) {
        is BreedListState.BreedList -> {
            val breedListState = state as BreedListState.BreedList
            val breedList = breedListState.breedList

            UiBreedList(breedList = breedList, onBreedClick = onBreedClick)
        }

        BreedListState.Error -> UiError()
        BreedListState.Loading -> UiLoading()
        BreedListState.NoData -> UiNoData()
    }
}