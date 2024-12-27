package com.example.domain.cat

import androidx.paging.PagingData
import com.example.domain.cat.models.CatModel
import kotlinx.coroutines.flow.Flow

interface CatRepository {

    fun getCatsPagingFlow(): Flow<PagingData<CatModel>>

}