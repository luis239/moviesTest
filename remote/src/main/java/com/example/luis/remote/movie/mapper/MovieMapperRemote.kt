package com.example.luis.remote.movie.mapper

import com.example.luis.remote.movie.model.MovieRemote
import com.example.luis.repository.movie.model.MovieData

fun MovieRemote.mapToData():MovieData{
    return MovieData(id,rating, imageUrl, title)
}