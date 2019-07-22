package com.example.luis.repository.movie.mapper

import com.example.luis.domain.movies.model.MovieModel
import com.example.luis.repository.movie.model.MovieData


fun MovieData.mapToModel() : MovieModel{
    return MovieModel(id,rating, imageUrl, title,isFavorite)
}

fun MovieModel.mapToData() : MovieData{
    return MovieData(id,rating, imageUrl, title, isFavorite)
}