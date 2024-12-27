package com.example.data.cat.remote

import com.example.data.cat.remote.models.CatApiModel


interface CatRemoteDataSource {

    suspend fun getCats(page:Int): Result<List<CatApiModel>>

}