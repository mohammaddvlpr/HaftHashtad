package com.example.data.cat.remote

import com.example.data.cat.remote.models.CatApiModel
import com.example.data.cat.remote.models.CatDetailApiModel


interface CatRemoteDataSource {

    suspend fun getCats(page:Int): Result<List<CatApiModel>>

    suspend fun getCatById(id:String):Result<CatDetailApiModel>

}