package com.example.domain.cat

import androidx.paging.PagingData
import com.example.domain.cat.models.CatDetailModel
import com.example.domain.cat.models.CatModel
import kotlinx.coroutines.flow.Flow

interface CatRepository {

    fun getCatsPagingFlow(): Flow<PagingData<CatModel>>

    suspend fun getCatDetailById(id:String): Result<CatDetailModel>


    suspend fun toggleFavourite(id:String)

    fun getFavouriteCatIdsFlow(): Flow<List<String>>

}