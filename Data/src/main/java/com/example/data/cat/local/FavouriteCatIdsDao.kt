package com.example.data.cat.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.cat.local.entity.FavouriteCatIdEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteCatIdsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCatIdEntity(item: FavouriteCatIdEntity)

    @Query("DELETE FROM favourite_cat_id_entity WHERE id = :id")
    suspend fun deleteCatIdEntity(id: String)

    @Query("SELECT * FROM favourite_cat_id_entity")
    suspend fun getAllFavouritesCatIds(): List<FavouriteCatIdEntity>

    @Query("SELECT COUNT(*) > 0 FROM favourite_cat_id_entity WHERE id = :id")
    suspend fun isFavourite(id:String): Boolean

    @Query("SELECT id FROM favourite_cat_id_entity")
    fun getAllFavouritesCatIdsFlow(): Flow<List<String>>
}