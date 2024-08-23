package com.catchallenge.repositories.cache.catroom

import androidx.room.Database
import androidx.room.RoomDatabase
import com.catchallenge.repositories.cache.catroom.entities.BreedEntity

@Database(entities = [BreedEntity::class], version = 1)
abstract class CatRoomDatabase : RoomDatabase() {
    abstract fun catDao(): CatRepositoryDao
}