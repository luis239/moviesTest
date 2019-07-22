package com.example.luis.repository.movie.model


data class MovieData(
    val id:Int?,
    val rating: Double?,
    val imageUrl: String?,
    val title: String?,
    var isFavorite:Boolean = false
)