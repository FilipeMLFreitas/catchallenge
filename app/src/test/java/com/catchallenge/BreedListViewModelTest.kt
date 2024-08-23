package com.catchallenge

import app.cash.turbine.test
import com.catchallenge.model.Breed
import com.catchallenge.repository.CatRepository
import com.catchallenge.repository.cache.CatCacheRepository
import com.catchallenge.ui.screens.breedlist.BreedListState
import com.catchallenge.ui.screens.breedlist.BreedListViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BreedListViewModelTest {
    private val catRepository = mockk<CatRepository>(relaxed = true)
    private val catCacheRepository = mockk<CatCacheRepository>(relaxed = true)

    private lateinit var viewModel: BreedListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = BreedListViewModel(catRepository, catCacheRepository)
    } // turn relaxUnitFun on for all mocks

    @Test
    fun `test get breeds with success returning a non empty list from cache`() = runTest {
        val breedList = listOf(mockk<Breed>(), mockk<Breed>())

        coEvery { catCacheRepository.getBreeds() } returns breedList

        viewModel.getBreeds()

        coVerify { catCacheRepository.getBreeds() }
        coVerify(exactly = 0) { catRepository.getBreeds() }

        viewModel.breedListStateFlow.test {
            val item = awaitItem()

            assert(item is BreedListState.BreedList)
            item as BreedListState.BreedList
            assertEquals(breedList, item.breedList)
        }
    }

    @Test
    fun `test get breeds with success returning a non empty list from the network`() = runTest {
        val breedList = listOf(mockk<Breed>(), mockk<Breed>())

        coEvery { catCacheRepository.getBreeds() } returns emptyList()
        coEvery { catRepository.getBreeds() } returns breedList

        viewModel.getBreeds()

        coVerify { catCacheRepository.getBreeds() }
        coVerify { catRepository.getBreeds() }
        coVerify { catCacheRepository.insertBreeds(breedList) }

        viewModel.breedListStateFlow.test {
            val item = awaitItem()

            assert(item is BreedListState.BreedList)
            item as BreedListState.BreedList
            assertEquals(breedList, item.breedList)
        }
    }

    @Test
    fun `test get breeds with network failure`() = runTest {
        val breedList = listOf(mockk<Breed>(), mockk<Breed>())

        coEvery { catCacheRepository.getBreeds() } returns emptyList()
        coEvery { catRepository.getBreeds() } throws Exception()

        viewModel.getBreeds()

        coVerify { catCacheRepository.getBreeds() }
        coVerify { catRepository.getBreeds() }
        coVerify(exactly = 0) { catCacheRepository.insertBreeds(breedList) }

        viewModel.breedListStateFlow.test {
            val item = awaitItem()

            assert(item is BreedListState.Error)
        }
    }
}