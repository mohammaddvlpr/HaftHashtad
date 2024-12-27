package com.example.data.cat

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.cat.local.CatLocalDataSource
import com.example.data.cat.remote.CatRemoteDataSource
import com.example.domain.cat.models.CatModel
import retrofit2.HttpException
import java.io.IOException

class CatPagingSource(
    private val catRemoteDataSource: CatRemoteDataSource,
    private val catLocalDataSource: CatLocalDataSource,
    private val mapper: CatDataMapper,
) : PagingSource<Int, CatModel>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatModel> {
        return try {
            val page = params.key ?: 0

            val response = catRemoteDataSource.getCats(page = page)

            val responseValue = response.getOrNull()

            return if (responseValue == null)
                LoadResult.Error(response.exceptionOrNull() ?: Exception("Unknown"))
            else {
                val favouriteIds = catLocalDataSource.getFavouriteCatIds()


                LoadResult.Page(
                    data = mapper.mapApiModelsToDomain(responseValue, favouriteIds),
                    nextKey = if (responseValue.size < PAGE_SIZE) null else page + 1,
                    prevKey = null
                )
            }

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CatModel>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}