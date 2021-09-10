package com.example.dogapi.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatImage(
    val url: String,
    val height: Int,
    val width: Int
): Parcelable
