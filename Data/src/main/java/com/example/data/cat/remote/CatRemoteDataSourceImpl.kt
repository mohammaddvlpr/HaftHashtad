package com.example.data.cat.remote

import com.example.data.apiCall
import com.example.data.cat.remote.models.CatApiModel
import com.example.data.cat.remote.models.CatDetailApiModel
import javax.inject.Inject

class CatRemoteDataSourceImpl @Inject constructor(private val catService: CatService) :
    CatRemoteDataSource {
    override suspend fun getCats(page: Int): Result<List<CatApiModel>> {
        return apiCall { catService.getCats(page) }
    }

    override suspend fun getCatById(id: String): Result<CatDetailApiModel> {
        return apiCall { catService.getCatDetailById(id) }
    }
}