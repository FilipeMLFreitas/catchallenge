package com.catchallenge.repositories

import com.catchallenge.model.Breed

interface CatRepository {
    suspend fun getBreeds(): List<Breed>
}