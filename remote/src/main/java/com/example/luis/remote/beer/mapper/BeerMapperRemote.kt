package com.example.luis.remote.beer.mapper

import com.example.luis.remote.beer.model.MovieRemote
import com.example.luis.repository.beer.model.RemoteData

fun MovieRemote.mapToData():RemoteData{
    return RemoteData(rating, imageUrl, title)
}