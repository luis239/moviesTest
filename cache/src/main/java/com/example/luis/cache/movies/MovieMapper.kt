package com.example.luis.cache.movies

import com.example.luis.repository.movie.model.MovieData


fun MovieData.toEntity() : MoviesEntity{
    return MoviesEntity(id!!,rating, imageUrl, title, isFavorite)
}

fun MoviesEntity.toData():MovieData{
    return MovieData(id,rating, imageUrl, title, isFavorite)
}