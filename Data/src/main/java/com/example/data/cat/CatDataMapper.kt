package com.example.data.cat

import com.example.data.cat.remote.models.CatApiModel
import com.example.data.cat.remote.models.CatDetailApiModel
import com.example.domain.cat.models.CatDetailModel
import com.example.domain.cat.models.CatModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CatDataMapper @Inject constructor() {

    suspend fun mapApiModelsToDomain(
        models: List<CatApiModel>
    ): List<CatModel> {
        return withContext(Dispatchers.Default) {
            models.map {
                with(it) {
                    CatModel(
                        id = id,
                        imageUrl = imageUrl,
                        name = breeds.firstOrNull()?.name ?: "Name is unknown"
                    )
                }
            }
        }
    }

    fun mapDetailCatApiModelToDomain(
        result: Result<CatDetailApiModel>,
        isFavourite: Boolean
    ): Result<CatDetailModel> {
        return result.map {
            with(it) {
                CatDetailModel(
                    id = id,
                    imageUrl = imageUrl,
                    isFavourite = isFavourite,
                    name = breeds.firstOrNull()?.name ?: "No information",
                    description = breeds.firstOrNull()?.description ?: "No information"
                )
            }
        }
    }


}