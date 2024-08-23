package com.catchallenge.repositories.thecatapi.mapping

import com.catchallenge.model.Breed
import com.catchallenge.repositories.thecatapi.dao.BreedDao
import com.catchallenge.repositories.util.Mapper


object BreedMapping : Mapper<BreedDao, Breed> {
    override fun map(from: BreedDao): Breed {
        return Breed(
            id = from.id,
            name = from.name,
            origin = from.origin,
            imageUrl = from.image?.url,
            description = from.description,
            temperament = from.temperament,
        )
    }
}