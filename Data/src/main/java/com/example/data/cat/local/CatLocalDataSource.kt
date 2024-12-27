package com.example.data.cat.local

import com.example.data.cat.local.entity.FavouriteCatIdEntity
import kotlinx.coroutines.flow.Flow

interface CatLocalDataSource {

    suspend fun getFavouriteCatIds(): List<FavouriteCatIdEntity>

    suspend fun isFavourite(id:String):Boolean

    suspend fun toggleFavourite(id: String)

    fun getFavouriteCatIdsFlow(): Flow<List<String>>


}