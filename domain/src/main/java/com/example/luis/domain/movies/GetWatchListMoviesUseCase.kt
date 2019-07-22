package com.example.luis.domain.movies

import com.example.luis.domain.movies.model.MovieModel
import com.example.luis.domain.common.executor.PostExecutionThread
import com.example.luis.domain.movies.repository.MovieRepository
import com.example.luis.interactor.MaybeUseCase
import io.reactivex.Maybe
import javax.inject.Inject

class GetWatchListMoviesUseCase @Inject constructor(
    postExecutionThread: PostExecutionThread,
    private val movieRepository: MovieRepository
):MaybeUseCase<List<MovieModel>,Nothing>(postExecutionThread) {
    override fun buildUseCaseObservable(params: Nothing?): Maybe<List<MovieModel>> {
        return movieRepository.getWatchList()
    }

}