package com.example.data.cat.remote.models

import com.google.gson.annotations.SerializedName

data class CatDetailApiModel(
    @SerializedName("id") val id: String,
    @SerializedName("url") val imageUrl: String,
    @SerializedName("breeds") val breeds: List<BreedDetailApiModel>,
)
