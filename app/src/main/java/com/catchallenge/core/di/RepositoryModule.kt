package com.catchallenge.core.di

import com.catchallenge.repository.CatRepository
import com.catchallenge.repository.thecatapi.TheCatApiRepositoryV1
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Qualifier

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @TheCatApiOkHttpClient
    @Provides
    fun provideTheCatApiOkHttpClient(
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.request().newBuilder()
                    //TODO: actually, the auth method of the repository shouldn't be knowledge of such a top level place such as this. need to find a way to have the TheCatApiRepository set it somehow
                    //TODO: move api key out (yes, i know this shouldn't be commited, but time is of the essence..)
                    .addHeader(
                        "x-api-key",
                        "live_0g89soDp3vfWBtym141PXnmK709047GVULI5n0Hrq2tSxtTBYfevqeGlwRnAJhyR"
                    )
                    .build()
                    .let(chain::proceed)
            }
            //TODO: only log for debug builds
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @ViewModelScoped
    @Provides
    fun provideCatRepository(
        @TheCatApiOkHttpClient okHttpClient: OkHttpClient
    ): CatRepository {
        return TheCatApiRepositoryV1("https://api.thecatapi.com/v1/", okHttpClient)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TheCatApiOkHttpClient