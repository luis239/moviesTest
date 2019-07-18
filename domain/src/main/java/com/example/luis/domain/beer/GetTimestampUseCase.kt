package com.example.luis.domain.beer

import com.example.luis.domain.common.executor.PostExecutionThread
import com.example.luis.domain.beer.repository.BeerRepository
import com.example.luis.interactor.MaybeUseCase
import io.reactivex.Maybe
import javax.inject.Inject

class GetTimestampUseCase @Inject constructor(
    postExecutionThread: PostExecutionThread,
    private val beerRepository: BeerRepository
):MaybeUseCase<String,Nothing>(postExecutionThread) {
    override fun buildUseCaseObservable(params: Nothing?): Maybe<String> {
        return beerRepository.gerDateInfo()
    }

}