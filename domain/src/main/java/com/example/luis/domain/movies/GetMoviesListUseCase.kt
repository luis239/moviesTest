package com.example.luis.domain.movies

import com.example.luis.domain.common.executor.PostExecutionThread
import com.example.luis.domain.movies.model.MovieModel
import com.example.luis.domain.movies.repository.MovieRepository
import com.example.luis.interactor.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetMoviesListUseCase @Inject constructor(
    postExecutionThread: PostExecutionThread,
    private val movieRepository: MovieRepository
):SingleUseCase<List<MovieModel>, GetMoviesListUseCase.Params>(postExecutionThread) {
    override fun buildUseCaseObservable(params: Params?): Single<List<MovieModel>> {
        return if(params!!.fromRemote) {
            movieRepository.getMoviesListRemote()
        }else{
            movieRepository.getMoviesList()
        }

    }

    data class Params(val fromRemote : Boolean)
}