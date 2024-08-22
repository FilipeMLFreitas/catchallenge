package com.catchallenge.repository.cache

import android.content.Context
import androidx.room.Room
import com.catchallenge.model.Breed
import com.catchallenge.repository.cache.catroom.CatRoomDatabase
import com.catchallenge.repository.cache.catroom.mapping.fromBreedEntityMapping
import com.catchallenge.repository.cache.catroom.mapping.toBreedEntityMapping
import com.catchallenge.repository.util.mapAll

class CatRoomRepository(context: Context) : CatCacheRepository {
    private val roomDatabase by lazy {
        Room.databaseBuilder(
            context,
            CatRoomDatabase::class.java, "cats"
        ).build()
    }

    override suspend fun getBreeds(): List<Breed> {
        val daoList = roomDatabase.catDao().getAll()
        return fromBreedEntityMapping.mapAll(daoList)
    }

    override suspend fun insertBreeds(breedList: List<Breed>) {
        for (breed in breedList) {
            roomDatabase.catDao().insert(toBreedEntityMapping.map(breed))
        }
    }
}