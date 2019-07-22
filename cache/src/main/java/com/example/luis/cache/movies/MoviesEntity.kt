package com.example.luis.cache.movies

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = MoviesConstants.TABLE_NAME)
data class MoviesEntity (
    @PrimaryKey
    var id: Int,
    val rating: Double?,
    val imageUrl: String?,
    val title: String?,
    var isFavorite:Boolean = false,
    var isWatchList:Boolean = false
)