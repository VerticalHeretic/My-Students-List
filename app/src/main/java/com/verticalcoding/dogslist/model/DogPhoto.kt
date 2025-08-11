package com.verticalcoding.dogslist.model

import kotlinx.serialization.Serializable

@Serializable
data class DogPhoto(
    val message: String,
    val status: String
)