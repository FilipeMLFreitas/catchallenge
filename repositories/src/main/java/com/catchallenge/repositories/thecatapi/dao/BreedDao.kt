package com.catchallenge.repositories.thecatapi.dao

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BreedDao(
    @SerialName("id")
    var id: String,

    @SerialName("name")
    var name: String,

    @SerialName("origin")
    var origin: String,

    @SerialName("temperament")
    var temperament: String,

    @SerialName("description")
    var description: String,

    @SerialName("life_span")
    var lifeSpan: String,

    @SerialName("image")
    var image: ImageDao?,
)