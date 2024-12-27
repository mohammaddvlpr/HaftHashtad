package com.example.data.cat.local

import com.example.data.cat.local.entity.FavouriteCatIdEntity
import javax.inject.Inject

class CatLocalDataSourceImpl @Inject constructor() :
    CatLocalDataSource {
    override suspend fun getFavouriteCatIds(): List<FavouriteCatIdEntity> {
        TODO("Not yet implemented")
    }


}