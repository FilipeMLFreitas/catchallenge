package com.catchallenge.repository

import com.catchallenge.model.Breed

interface CatRepository {
    suspend fun getBreeds(): List<Breed>
}