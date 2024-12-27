package com.example.hafthashtad.screen.detail.models

import com.example.domain.cat.models.CatDetailModel
import javax.inject.Inject

class DetailScreenMapper @Inject constructor() {

    fun mapNewModelToNewsDetailUiModel(model: CatDetailModel) = with(model) {
        CatDetailUiModel(id, name, imageUrl, description, isFavourite)
    }
}