package com.catchallenge.repositories.cache.catroom

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.catchallenge.repositories.cache.catroom.entities.BreedEntity

@Dao
interface CatRepositoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(breedEntity: BreedEntity): Long

    @Query("SELECT * FROM breeds ORDER BY name ASC")
    suspend fun getAll(): List<BreedEntity>

    @Query("DELETE FROM breeds")
    suspend fun deleteAll()
}