package com.example.domain.cat.models

data class CatDetailModel(
    val id: String,
    val imageUrl: String,
    val isFavourite: Boolean,
    val name: String,
    val description: String
)
