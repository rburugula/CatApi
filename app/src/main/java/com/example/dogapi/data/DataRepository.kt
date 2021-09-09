package com.example.dogapi.data

import com.example.dogapi.api.ApiServiceInterface
import com.example.dogapi.model.CatBreed
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val apiServiceInterface: ApiServiceInterface
) {
    suspend fun getCatBreeds(): Result<ArrayList<CatBreed>> = kotlin.runCatching {
        apiServiceInterface.getCatBreeds()
    }
}