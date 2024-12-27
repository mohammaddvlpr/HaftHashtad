package com.example.data.cat

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.cat.local.CatLocalDataSource
import com.example.data.cat.remote.CatRemoteDataSource
import com.example.domain.cat.CatRepository
import com.example.domain.cat.models.CatDetailModel
import com.example.domain.cat.models.CatModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val catRemoteDataSource: CatRemoteDataSource,
    private val catLocalDataSource: CatLocalDataSource,
    private val mapper: CatDataMapper,
) : CatRepository {

    override fun getCatsPagingFlow(): Flow<PagingData<CatModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE
            ),
        ) {
            CatPagingSource(catRemoteDataSource, mapper)
        }.flow

    }

    override suspend fun getCatDetailById(id: String): Result<CatDetailModel> {
        return mapper.mapDetailCatApiModelToDomain(
            catRemoteDataSource.getCatById(id),
            catLocalDataSource.isFavourite(id)
        )
    }

    override suspend fun toggleFavourite(id: String) {
        catLocalDataSource.toggleFavourite(id)
    }

    override fun getFavouriteCatIdsFlow(): Flow<List<String>> {
        return catLocalDataSource.getFavouriteCatIdsFlow()
    }
}