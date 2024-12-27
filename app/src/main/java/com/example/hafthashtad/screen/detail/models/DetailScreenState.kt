package com.example.hafthashtad.screen.detail.models

data class DetailScreenState(
    val catDetailUiModel: CatDetailUiModel = CatDetailUiModel(
        id = "0",
        name = "",
        imageUrl = "",
        description = "",
        isFavourite = false
    )
)
