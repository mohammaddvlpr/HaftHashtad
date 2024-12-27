package com.example.data.cat.remote.models

import com.google.gson.annotations.SerializedName

data class BreedApiModel(
    @SerializedName("name") val name:String,
)
