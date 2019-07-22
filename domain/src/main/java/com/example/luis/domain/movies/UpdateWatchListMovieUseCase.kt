package com.example.luis.domain.movies

import com.example.luis.domain.movies.repository.MovieRepository
import com.example.luis.domain.common.executor.PostExecutionThread
import com.example.luis.interactor.CompletableUseCase
import io.reactivex.Completable
import javax.inject.Inject

class UpdateWatchListMovieUseCase @Inject constructor(
    postExecutionThread: PostExecutionThread,
    private val movieRepository: MovieRepository
):CompletableUseCase<UpdateWatchListMovieUseCase.Params>(postExecutionThread) {
    override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null")
        return movieRepository.updateWatchList(params.idMovie,params.isWatchList)
    }

    data class Params(val idMovie: Int,val isWatchList:Boolean)


}