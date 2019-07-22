package com.example.luis.domain.movies

import com.example.luis.domain.movies.model.MovieModel
import com.example.luis.domain.common.executor.PostExecutionThread
import com.example.luis.domain.movies.repository.MovieRepository
import com.example.luis.interactor.CompletableUseCase
import io.reactivex.Completable
import javax.inject.Inject

class SaveMoviesUseCase @Inject constructor(
    postExecutionThread: PostExecutionThread,
    private val movieRepository: MovieRepository
):CompletableUseCase<List<MovieModel>>(postExecutionThread) {
    override fun buildUseCaseCompletable(params: List<MovieModel>?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null")
        return movieRepository.saveMovies(params)
    }

    data class Params(val moviesModel: List<MovieModel>)


}