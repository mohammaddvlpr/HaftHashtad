package com.example.hafthashtad.di

import com.example.data.cat.local.CatLocalDataSource
import com.example.data.cat.local.CatLocalDataSourceImpl
import com.example.data.cat.remote.CatRemoteDataSource
import com.example.data.cat.remote.CatRemoteDataSourceImpl
import com.example.data.cat.remote.CatService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
abstract class CatDataModule {

    companion object {
        @Provides
        fun provideCatService(
            okHttpClient: OkHttpClient,
            gsonConverterFactory: GsonConverterFactory
        ): CatService {

            return Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com/v1/")
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build()
                .create(CatService::class.java)
        }

    }

    @Binds
    abstract fun bindCatRemoteDataSource(impl: CatRemoteDataSourceImpl): CatRemoteDataSource

    @Binds
    abstract fun bindCatLocalDataSource(impl: CatLocalDataSourceImpl): CatLocalDataSource

}