package com.example.data.cat.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.cat.local.entity.FavouriteCatIdEntity

@Dao
interface FavouriteCatIdDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCatIdEntity(item: FavouriteCatIdEntity)

    @Query("DELETE FROM favourite_cat_id_entity WHERE id = :id")
    suspend fun deleteCatIdEntity(id: String)

    @Query("SELECT * FROM favourite_cat_id_entity")
    suspend fun getAllFavouritesCatIds(): List<FavouriteCatIdEntity>

}