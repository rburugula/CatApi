package com.example.dogapi.api

import com.example.dogapi.model.CatBreed
import retrofit2.http.GET

interface ApiServiceInterface {
    @GET("breeds")
    suspend fun getCatBreeds(): ArrayList<CatBreed>

}