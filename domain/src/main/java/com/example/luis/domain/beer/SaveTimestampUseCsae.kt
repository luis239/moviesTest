package com.example.luis.domain.beer

import com.example.luis.domain.common.executor.PostExecutionThread
import com.example.luis.domain.beer.repository.BeerRepository
import com.example.luis.interactor.CompletableUseCase
import io.reactivex.Completable
import org.joda.time.DateTime
import javax.inject.Inject

class SaveTimestampUseCsae @Inject constructor(
    postExecutionThread: PostExecutionThread,
    private val beerRepository: BeerRepository
):CompletableUseCase<Nothing>(postExecutionThread) {
    override fun buildUseCaseCompletable(params: Nothing?): Completable {
        return beerRepository.saveInfo(DateTime.now())
    }



}