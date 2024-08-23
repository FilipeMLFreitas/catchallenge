package com.catchallenge.repositories.thecatapi


import com.catchallenge.model.Breed
import com.catchallenge.repositories.CatRepository
import com.catchallenge.repositories.thecatapi.mapping.BreedMapping
import com.catchallenge.repositories.util.mapAll
import okhttp3.OkHttpClient

class TheCatApiRepositoryV1(
    private val baseUrl: String,
    private val okHttpClient: OkHttpClient?,
) :
    CatRepository {
    private val api: RepositoryContract by lazy {
        //TODO: maybe have RetrofitApiBuilder injected to make it easier for testing
        com.catchallenge.repositories.thecatapi.util.RetrofitApiBuilder().createApi(
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