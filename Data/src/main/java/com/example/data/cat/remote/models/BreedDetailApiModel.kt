package com.example.data.cat.remote.models

import com.google.gson.annotations.SerializedName

data class BreedDetailApiModel(
    @SerializedName("name") val name:String,
    @SerializedName("description") val description:String,
)
