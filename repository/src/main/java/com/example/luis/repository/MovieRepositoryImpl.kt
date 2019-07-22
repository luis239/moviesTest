package com.example.luis.repository

import com.example.luis.domain.movies.model.MovieModel
import com.example.luis.domain.movies.repository.MovieRepository
import com.example.luis.repository.movie.mapper.mapToData
import com.example.luis.repository.movie.mapper.mapToModel
import com.example.luis.repository.movie.repository.MovieRemote
import com.example.luis.repository.movie.repository.MovieCache
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject


class MovieRepositoryImpl  @Inject constructor(
    private val movieCache: MovieCache,
    private val movieRemote: MovieRemote
): MovieRepository {

    override fun getWatchList(): Maybe<List<MovieModel>> {
        return movieCache.getWatchList().map {
            it.map {movie->
                movie.mapToModel()
            }
        }
    }

    override fun updateWatchList(idMovie: Int, isWatchList: Boolean): Completable {
        return movieCache.updateWatchList(idMovie,isWatchList)
    }

    override fun updateFavoriteMovie(idMovie: Int,isFavorite:Boolean): Completable {
        return movieCache.updateFavorite(idMovie,isFavorite)
    }

    override fun getMoviesListRemote(): Single<List<MovieModel>> {
        return movieRemote.getMovies().map {
            it.map { movieData->
                movieData.mapToModel()
            }
        }.doOnSuccess {
            saveMovies(it).subscribe()
        }
    }

    override fun getFavoriteMovies(): Maybe<List<MovieModel>> {
        return movieCache.getFavoriteMovies().map {
            it.map {
                it.mapToModel()
            }
        }
    }

    override fun saveMovies(moviesModel: List<MovieModel>): Completable {
        return movieCache.saveMovie(moviesModel.map {
            it.mapToData()
        })
    }

    override fun getMoviesList(): Single<List<MovieModel>> {
        return movieCache.getMovies().flatMapSingle {
            if(it.isEmpty())
                getMoviesListRemote()
            else {
                Single.just(it.map { movie ->
                    movie.mapToModel()
                })
            }
        }
    }



}