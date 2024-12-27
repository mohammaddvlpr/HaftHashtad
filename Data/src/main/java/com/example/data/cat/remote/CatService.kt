package com.example.data.cat.remote

import com.example.data.cat.PAGE_SIZE
import com.example.data.cat.remote.models.CatApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CatService {

    @GET("images/search?limit=$PAGE_SIZE&has_breeds=1&order=DESC")
    suspend fun getCats(
        @Query("page") page: Int
    ): List<CatApiModel>

}