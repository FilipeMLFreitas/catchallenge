package com.catchallenge.repository.thecatapi.dao

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageDao(
    @SerialName("url")
    var url: String,
)