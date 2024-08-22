package com.catchallenge.repository.thecatapi.mapping

import com.catchallenge.model.Breed
import com.catchallenge.repository.thecatapi.dao.BreedDao

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