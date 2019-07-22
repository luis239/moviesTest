package com.example.luis.remote.movie

import com.example.luis.remote.movie.mapper.mapToData
import com.example.luis.remote.common.Api
import com.example.luis.repository.movie.model.MovieData
import com.example.luis.repository.movie.repository.MovieRemote
import io.reactivex.Single
import javax.inject.Inject

class MovieRemoteImpl @Inject constructor(
    private val service:Api
): MovieRemote {
    override fun getMovies(): Single<List<MovieData>> {
        return service.getListMovies("482d7fcac807d5d7dc7012b9e5479f6e","en-US",1).map {
            it.results?.map { movie->
                movie.mapToData()
            }
        }
    }


}