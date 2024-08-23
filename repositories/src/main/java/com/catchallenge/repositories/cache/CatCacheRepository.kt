package com.catchallenge.repositories.cache

import com.catchallenge.model.Breed

interface CatCacheRepository {
    suspend fun getBreeds(): List<Breed>

    suspend fun insertBreeds(breedList: List<Breed>)

    suspend fun removeAllBreeds()
}