package com.example.data.cat.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_cat_id_entity")
data class FavouriteCatIdEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
)
