package com.verticalcoding.dogslist.data.network

import com.verticalcoding.dogslist.model.DogPhoto
import retrofit2.http.GET

interface DogsService {

    @GET("image/random")
    suspend fun getRandomDogImage(): DogPhoto
}