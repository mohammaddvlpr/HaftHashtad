package com.example.data.cat.local

import com.example.data.cat.local.entity.FavouriteCatIdEntity
import javax.inject.Inject

class CatLocalDataSourceImpl @Inject constructor(private val favouriteCatIdsDao: FavouriteCatIdsDao) :
    CatLocalDataSource {
    override suspend fun getFavouriteCatIds(): List<FavouriteCatIdEntity> {
        return favouriteCatIdsDao.getAllFavouritesCatIds()
    }

    override suspend fun isFavourite(id: String): Boolean {
        return favouriteCatIdsDao.isFavourite(id)
    }


}