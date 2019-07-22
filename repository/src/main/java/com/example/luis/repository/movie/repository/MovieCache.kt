package com.example.luis.repository.movie.repository


import com.example.luis.repository.movie.model.MovieData
import io.reactivex.Completable
import io.reactivex.Maybe

interface MovieCache {
    fun saveMovie(moviesData: List<MovieData>):Completable
    fun getMovies(): Maybe<List<MovieData>>
    fun updateFavorite(id:Int,isFavorite:Boolean):Completable
    fun updateWatchList(id:Int,isWatchList:Boolean):Completable
    fun getFavoriteMovies():Maybe<List<MovieData>>
    fun getWatchList():Maybe<List<MovieData>>

}