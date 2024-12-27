package com.example.data.cat.remote

import com.example.data.cat.PAGE_SIZE
import com.example.data.cat.remote.models.CatApiModel
import com.example.data.cat.remote.models.CatDetailApiModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CatService {

    @GET("images/search?limit=$PAGE_SIZE&has_breeds=1&order=DESC")
    suspend fun getCats(
        @Query("page") page: Int
    ): List<CatApiModel>

    @GET("images/{id}")
    suspend fun getCatDetailById(
        @Path("id") id: String
    ): CatDetailApiModel

}