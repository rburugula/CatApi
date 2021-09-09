package com.example.dogapi.model

data class CatBreed(
    val name: String,
    val image: CatImage,
    val temperament: String,
    val wikipedia_url: String,
    val energy_level: Int
)