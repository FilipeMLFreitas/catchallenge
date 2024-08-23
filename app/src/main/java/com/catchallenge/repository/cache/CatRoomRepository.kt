package com.catchallenge.repository.cache

import android.content.Context
import androidx.room.Room
import com.catchallenge.model.Breed
import com.catchallenge.repository.cache.catroom.CatRoomDatabase
import com.catchallenge.repository.cache.catroom.mapping.FromBreedEntityMapping
import com.catchallenge.repository.cache.catroom.mapping.ToBreedEntityMapping
import com.catchallenge.repository.util.mapAll

class CatRoomRepository(context: Context) : CatCacheRepository {
    private val roomDatabase by lazy {
        //TODO: the database builder could be injected to make it easier for testing
        Room.databaseBuilder(
            context,
            CatRoomDatabase::class.java, "cats"
        ).build()
    }

    override suspend fun getBreeds(): List<Breed> {
        val daoList = roomDatabase.catDao().getAll()
        return FromBreedEntityMapping.mapAll(daoList)
    }

    override suspend fun insertBreeds(breedList: List<Breed>) {
        for (breed in breedList) {
            roomDatabase.catDao().insert(ToBreedEntityMapping.map(breed))
        }
    }

    override suspend fun removeAllBreeds() {
        roomDatabase.catDao().deleteAll()
    }
}