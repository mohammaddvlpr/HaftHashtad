package com.example.domain.cat.useCase

import androidx.paging.PagingData
import com.example.domain.cat.CatRepository
import com.example.domain.cat.models.CatModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCatsPagingFlowUseCase @Inject constructor(private val catRepository: CatRepository) {

    operator fun invoke(): Flow<PagingData<CatModel>> {
        return catRepository.getCatsPagingFlow()
    }
}