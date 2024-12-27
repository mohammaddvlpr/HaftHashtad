package com.example.data.cat.local

import com.example.data.cat.local.entity.FavouriteCatIdEntity

interface CatLocalDataSource {

    suspend fun getFavouriteCatIds(): List<FavouriteCatIdEntity>

}