package com.example.hafthashtad.screen.home.models

import com.example.domain.cat.models.CatModel
import javax.inject.Inject

class HomeScreenUiMapper @Inject constructor() {

    fun mapDomainToUi(model: CatModel) = with(model) {
        CatUiModel(id, name, imageUrl)
    }
}