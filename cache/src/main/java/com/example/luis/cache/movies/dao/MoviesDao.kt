package com.example.luis.cache.movies.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.luis.cache.movies.MoviesConstants
import com.example.luis.cache.movies.MoviesEntity
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
abstract class MoviesDao {

    @Query(MoviesConstants.GET_MOVIES)
    abstract fun getMovies(): Maybe<List<MoviesEntity>>
    @Query(MoviesConstants.GET_FAVORITE_MOVIES)
    abstract fun getFavoriteMovies(): Maybe<List<MoviesEntity>>
    @Query(MoviesConstants.GET_WATCH_LIST)
    abstract fun getWatchList(): Maybe<List<MoviesEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovies(moviesEntity: List<MoviesEntity>):Completable
    @Query(MoviesConstants.UPDATE_FAVORITE)
    abstract fun updateFavorite(id:Int,isFavorite:Boolean):Completable
    @Query(MoviesConstants.UPDATE_WATCH_LIST)
    abstract fun updateWatchList(id:Int,isWatchList:Boolean):Completable


}
