package com.catchallenge.core.di

import android.content.Context
import com.catchallenge.BuildConfig
import com.catchallenge.repositories.CatRepository
import com.catchallenge.repositories.cache.CatCacheRepository
import com.catchallenge.repositories.cache.CatRoomRepository
import com.catchallenge.repositories.thecatapi.TheCatApiRepositoryV1
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
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
                    .addHeader(
                        "x-api-key",
                        BuildConfig.CATAPIKEY
                    )
                    //TODO: maybe set certificate pinning for higher security
                    .build()
                    .let(chain::proceed)
            }.also {
                if (BuildConfig.DEBUG)
                    it.addInterceptor(loggingInterceptor)
            }
            .build()
    }

    @Provides
    fun provideCatRepository(
        @TheCatApiOkHttpClient okHttpClient: OkHttpClient
    ): CatRepository {
        //we'll use TheCatApi for our cat source
        return TheCatApiRepositoryV1(BuildConfig.CATAPIURL, okHttpClient)
    }

    @Provides
    fun provideCatCacheRepository(@ApplicationContext appContext: Context): CatCacheRepository {
        //we'll use a cache based on the room database
        return CatRoomRepository(appContext)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TheCatApiOkHttpClient