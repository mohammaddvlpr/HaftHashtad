package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.cat.local.FavouriteCatIdsDao
import com.example.data.cat.local.entity.FavouriteCatIdEntity

@Database(entities = [FavouriteCatIdEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favouriteCatIdsDao(): FavouriteCatIdsDao
}