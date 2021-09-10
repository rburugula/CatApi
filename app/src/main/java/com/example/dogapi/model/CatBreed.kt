package com.example.dogapi.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatBreed(
    val name: String,
    val image: CatImage,
    val temperament: String,
    val wikipedia_url: String,
    val energy_level: Int
): Parcelable