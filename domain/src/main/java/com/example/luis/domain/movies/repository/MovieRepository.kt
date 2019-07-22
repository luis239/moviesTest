package com.example.luis.domain.movies.repository

import com.example.luis.domain.movies.model.MovieModel
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface MovieRepository {
    fun saveMovies(moviesModel: List<MovieModel>):Completable
    fun getFavoriteMovies(): Maybe<List<MovieModel>>
    fun getMoviesList(): Single<List<MovieModel>>
    fun getMoviesListRemote(): Single<List<MovieModel>>
    fun updateFavoriteMovie(idMovie:Int,isFavorite:Boolean) : Completable
    fun getWatchList(): Maybe<List<MovieModel>>
    fun updateWatchList(idMovie:Int,isWatchList:Boolean) : Completable
}