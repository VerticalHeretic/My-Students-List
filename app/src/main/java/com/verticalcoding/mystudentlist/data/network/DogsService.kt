package com.verticalcoding.mystudentlist.data.network

import com.verticalcoding.mystudentlist.model.DogPhoto
import retrofit2.http.GET

interface DogsService {

    @GET("image/random")
    suspend fun getRandomDogImage(): DogPhoto
}