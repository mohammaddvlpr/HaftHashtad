package com.example.domain.cat.useCase

import com.example.domain.cat.CatRepository
import javax.inject.Inject

class ToggleFavouriteUseCase @Inject constructor(private val catRepository: CatRepository) {

    suspend fun invoke(id: String) {
        return catRepository.toggleFavourite(id)
    }
}