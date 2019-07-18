package com.example.luis.repository

import com.example.luis.domain.beer.model.MovieModel
import com.example.luis.domain.beer.repository.BeerRepository
import com.example.luis.repository.beer.mapper.mapToModel
import com.example.luis.repository.beer.repository.BeerRemote
import com.example.luis.repository.beer.repository.BeerCache
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import org.joda.time.DateTime
import javax.inject.Inject


class BeerRepositoryImpl  @Inject constructor(
    private val beerCache: BeerCache,
    private val beerRemote: BeerRemote
): BeerRepository {
    override fun saveInfo(date:DateTime): Completable {
        return beerCache.saveBeerInfo(date)


    }

    override fun getBeerList(): Single<List<MovieModel>> {
        return beerRemote.getBeers().map {
            it.map {
                it.mapToModel()
            }
        }
    }

    override fun gerDateInfo(): Maybe<String> {
        return beerCache.getDateInfo()
    }

}