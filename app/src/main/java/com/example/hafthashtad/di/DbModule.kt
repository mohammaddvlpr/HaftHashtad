package com.example.hafthashtad.di

import android.content.Context
import androidx.room.Room
import com.example.data.cat.local.FavouriteCatIdsDao
import com.example.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DbModule {


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context = context,
            AppDatabase::class.java,
            "snappbox_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideFavouriteCatIdsDao(appDatabase: AppDatabase): FavouriteCatIdsDao {
        return  appDatabase.favouriteCatIdsDao()
    }
}