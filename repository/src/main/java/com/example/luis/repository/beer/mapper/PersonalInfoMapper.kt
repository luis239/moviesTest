package com.example.luis.repository.beer.mapper

import com.example.luis.domain.beer.model.MovieModel
import com.example.luis.repository.beer.model.RemoteData


fun RemoteData.mapToModel() : MovieModel{
    return MovieModel(rating, imageUrl, title)
}