package com.example.data.cat

import com.example.data.cat.local.entity.FavouriteCatIdEntity
import com.example.data.cat.remote.models.CatApiModel
import com.example.domain.cat.models.CatModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CatDataMapper @Inject constructor() {

    suspend fun mapApiModelsToDomain(
        models: List<CatApiModel>,
        favouriteIds: List<FavouriteCatIdEntity>
    ): List<CatModel> {
        return withContext(Dispatchers.Default) {
            models.map {
                with(it) {
                    CatModel(
                        id = id,
                        imageUrl = imageUrl,
                        isFavourite = favouriteIds.any { idEntity -> idEntity.id == id },
                        name = breeds.first().name
                    )
                }
            }
        }
    }


}