package com.catchallenge.repositories.thecatapi

import com.catchallenge.repositories.thecatapi.dao.BreedDao
import retrofit2.http.GET

interface RepositoryContract {
    @GET("breeds")
    suspend fun getBreeds(): List<BreedDao>
}