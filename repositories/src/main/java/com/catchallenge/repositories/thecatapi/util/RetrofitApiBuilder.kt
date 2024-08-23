package com.catchallenge.repositories.thecatapi.util


import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class RetrofitApiBuilder {
    //TODO: maybe find an alternative to avoid experimental api
    @OptIn(ExperimentalSerializationApi::class)
    fun <T> createApi(
        baseUrl: String,
        apiDefinition: Class<T>,
        okHttpClient: OkHttpClient?,
    ): T {

        val networkJson = Json {
            //these properties make deserialization more flexible (such as with null values)
            ignoreUnknownKeys = true
            explicitNulls = false
        }

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                networkJson.asConverterFactory(
                    "application/json; charset=UTF8".toMediaType()
                )
            )
        okHttpClient?.let {
            retrofitBuilder.client(okHttpClient)
        }

        val retrofit = retrofitBuilder.build()

        return retrofit.create(apiDefinition)
    }
}