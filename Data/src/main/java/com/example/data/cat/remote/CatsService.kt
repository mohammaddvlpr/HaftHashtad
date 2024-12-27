package com.example.data.cat.remote

import com.example.data.cat.remote.models.CatApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsService {

    @GET("images/search?limit=10&has_breeds=1&order=DESC")
    suspend fun getCats(
        @Query("page") page: Int
    ): List<CatApiModel>

}