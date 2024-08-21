package com.catchallenge.repository.thecatapi

import com.catchallenge.model.Breed
import com.catchallenge.repository.CatRepository
import com.catchallenge.repository.retrofit.RetrofitApiBuilder
import okhttp3.OkHttpClient

class TheCatApiRepository(
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

        val list = api.getBreeds()

        //TODO: mapping to app model

        return emptyList()
    }
}