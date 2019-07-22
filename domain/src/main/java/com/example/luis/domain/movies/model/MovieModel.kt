package com.example.luis.domain.movies.model


data class MovieModel(
    val id:Int?,
    val rating: Double?,
    val imageUrl: String?,
    val title: String?,
    var isFavorite:Boolean = false
)