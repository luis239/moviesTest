package com.example.luis.domain.beer

import com.example.luis.domain.common.executor.PostExecutionThread
import com.example.luis.domain.beer.model.MovieModel
import com.example.luis.domain.beer.repository.BeerRepository
import com.example.luis.interactor.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetBeerListUseCase @Inject constructor(
    postExecutionThread: PostExecutionThread,
    private val beerRepository: BeerRepository
):SingleUseCase<List<MovieModel>,Nothing>(postExecutionThread) {
    override fun buildUseCaseObservable(params: Nothing?): Single<List<MovieModel>> {
        return beerRepository.getBeerList()
    }

}