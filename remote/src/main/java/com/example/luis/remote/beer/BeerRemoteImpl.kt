package com.example.luis.remote.beer

import com.example.luis.remote.beer.mapper.mapToData
import com.example.luis.remote.common.Api
import com.example.luis.repository.beer.model.RemoteData
import com.example.luis.repository.beer.repository.BeerRemote
import io.reactivex.Single
import javax.inject.Inject

class BeerRemoteImpl @Inject constructor(
    private val service:Api
): BeerRemote {
    override fun getBeers(): Single<List<RemoteData>> {
        return service.getListMovies("482d7fcac807d5d7dc7012b9e5479f6e","en-US",1).map {
            it.results?.map {
                it.mapToData()
            }
        }
    }


}