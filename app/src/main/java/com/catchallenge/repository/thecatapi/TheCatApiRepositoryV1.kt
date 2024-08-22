package com.catchallenge.repository.thecatapi

import com.catchallenge.model.Breed
import com.catchallenge.repository.CatRepository
import com.catchallenge.repository.thecatapi.mapping.BreedMapping
import com.catchallenge.repository.thecatapi.util.RetrofitApiBuilder
import com.catchallenge.repository.util.mapAll
import okhttp3.OkHttpClient

class TheCatApiRepositoryV1(
    private val baseUrl: String,
    private val okHttpClient: OkHttpClient?,
) :
    CatRepository {
    private val api: RepositoryContract by lazy {
        RetrofitApiBuilder().createApi(
            baseUrl,
            RepositoryContract::class.java,
            okHttpClient,
        )
    }

    override suspend fun getBreeds(): List<Breed> {
        val daoList = api.getBreeds()
        return BreedMapping.mapAll(daoList)
    }
}