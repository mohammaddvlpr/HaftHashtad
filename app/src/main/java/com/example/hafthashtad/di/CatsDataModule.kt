package com.example.hafthashtad.di

import com.example.data.cat.remote.CatsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
abstract class CatsDataModule {

    companion object {
        @Provides
        fun provideNewsService(
            okHttpClient: OkHttpClient,
            gsonConverterFactory: GsonConverterFactory
        ): CatsService {

            return Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com/v1/")
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build()
                .create(CatsService::class.java)
        }

    }

}