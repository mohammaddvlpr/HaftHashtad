package com.example.domain.cat.useCase

import com.example.domain.cat.CatRepository
import com.example.domain.cat.models.CatDetailModel
import javax.inject.Inject

class GetCatDetailByIDUseCase @Inject constructor(private val catRepository: CatRepository) {

    suspend operator fun invoke(id:String): Result<CatDetailModel> {
        return catRepository.getCatDetailById(id)
    }
}