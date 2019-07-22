package com.example.luis.cache.movies

import com.example.luis.cache.room.AppDb
import com.example.luis.repository.movie.model.MovieData
import com.example.luis.repository.movie.repository.MovieCache
import io.reactivex.Completable
import io.reactivex.Maybe
import javax.inject.Inject

class MoviesCacheImple @Inject constructor(
    private val appDb: AppDb
) : MovieCache {
    override fun updateWatchList(id: Int, isWatchList: Boolean): Completable {
        return Completable.defer {
            appDb.moviesDao().updateWatchList(id,isWatchList)
        }
    }

    override fun getWatchList(): Maybe<List<MovieData>> {
        return appDb.moviesDao().getWatchList().map {
            it.map {
                    moviesEntity ->
                moviesEntity.toData()
            }
        }
    }

    override fun getFavoriteMovies(): Maybe<List<MovieData>> {
     return appDb.moviesDao().getFavoriteMovies().map {
         it.map {
             moviesEntity ->
             moviesEntity.toData()
         }
     }
    }

    override fun updateFavorite(id: Int, isFavorite: Boolean): Completable {
        return Completable.defer {
            appDb.moviesDao().updateFavorite(id,isFavorite)
        }
    }

    override fun saveMovie(moviesData: List<MovieData>): Completable {
        return Completable.defer {
            appDb.moviesDao().insertMovies(moviesData.map {
             it.toEntity()
            })
        }
    }

    override fun getMovies(): Maybe<List<MovieData>> {
        return appDb.moviesDao().getMovies().map {
                it.map { movies ->
                    movies.toData()
                }

        }
    }

}