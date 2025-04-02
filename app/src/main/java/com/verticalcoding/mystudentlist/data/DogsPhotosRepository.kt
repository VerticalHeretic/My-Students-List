package com.verticalcoding.mystudentlist.data

import com.verticalcoding.mystudentlist.data.network.DogsService
import com.verticalcoding.mystudentlist.model.DogPhoto

interface DogsPhotosRepository {
    suspend fun getRandomDogImage(): DogPhoto
}

class NetworkDogsPhotosRepository(
    private val dogsService: DogsService,
) : DogsPhotosRepository {

    override suspend fun getRandomDogImage(): DogPhoto = dogsService.getRandomDogImage()
}