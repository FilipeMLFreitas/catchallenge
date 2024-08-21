package com.catchallenge.repository.thecatapi

import com.catchallenge.repository.thecatapi.dao.BreedDao
import retrofit2.http.GET

interface RepositoryContract {
    @GET("breeds")
    suspend fun getBreeds(): List<BreedDao>
}