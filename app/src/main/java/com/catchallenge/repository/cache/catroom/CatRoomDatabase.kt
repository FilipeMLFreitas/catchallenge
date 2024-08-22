package com.catchallenge.repository.cache.catroom

import androidx.room.Database
import androidx.room.RoomDatabase
import com.catchallenge.repository.cache.catroom.entities.BreedEntity

@Database(entities = [BreedEntity::class], version = 1)
abstract class CatRoomDatabase : RoomDatabase() {
    abstract fun catDao(): CatRepositoryDao
}