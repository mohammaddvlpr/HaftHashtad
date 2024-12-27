package com.example.domain.cat.useCase

import com.example.domain.cat.CatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavouriteCatIdsFlowUseCase @Inject constructor(private val catRepository: CatRepository) {

    operator fun invoke(): Flow<List<String>> {
        return catRepository.getFavouriteCatIdsFlow()
    }
}