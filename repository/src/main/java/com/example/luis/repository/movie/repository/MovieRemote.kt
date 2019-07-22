package com.example.luis.repository.movie.repository

import com.example.luis.repository.movie.model.MovieData
import io.reactivex.Single

interface MovieRemote {

        fun getMovies() : Single<List<MovieData>>
}