package com.verticalcoding.mystudentlist.model

import kotlinx.serialization.Serializable

@Serializable
data class DogPhoto(
    val message: String,
    val status: String
)