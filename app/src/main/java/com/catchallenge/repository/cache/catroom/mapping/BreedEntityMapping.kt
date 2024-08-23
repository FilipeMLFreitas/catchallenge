package com.catchallenge.repository.cache.catroom.mapping

import com.catchallenge.model.Breed
import com.catchallenge.repository.cache.catroom.entities.BreedEntity
import com.catchallenge.repository.util.Mapper

object FromBreedEntityMapping : Mapper<BreedEntity, Breed> {
    override fun map(from: BreedEntity): Breed {
        return Breed(
            id = from.id,
            name = from.name,
            origin = from.origin,
            imageUrl = from.imageUrl,
            description = from.description,
            temperament = from.temperament,
        )
    }
}

object ToBreedEntityMapping : Mapper<Breed, BreedEntity> {
    override fun map(from: Breed): BreedEntity {
        return BreedEntity(
            id = from.id,
            name = from.name,
            origin = from.origin,
            imageUrl = from.imageUrl,
            description = from.description,
            temperament = from.temperament,
        )
    }
}