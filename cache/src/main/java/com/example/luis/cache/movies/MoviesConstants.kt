package com.example.luis.cache.movies

object MoviesConstants{
    const val TABLE_NAME = "movies"
    const val GET_MOVIES = "SELECT * FROM $TABLE_NAME"
    const val GET_FAVORITE_MOVIES = "SELECT * FROM $TABLE_NAME WHERE isFavorite = 1"
    const val GET_WATCH_LIST = "SELECT * FROM $TABLE_NAME WHERE isWatchList = 1"
    const val UPDATE_FAVORITE = "UPDATE $TABLE_NAME SET isFavorite = :isFavorite WHERE id = :id"
    const val UPDATE_WATCH_LIST = "UPDATE $TABLE_NAME SET isWatchList = :isWatchList WHERE id = :id"
}