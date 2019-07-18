package com.example.luis.cache.beers

import com.example.luis.cache.room.AppDb
import com.example.luis.repository.beer.repository.BeerCache
import io.reactivex.Completable
import io.reactivex.Maybe
import org.joda.time.DateTime
import javax.inject.Inject

class BeersCacheImple @Inject constructor(
    private val appDb: AppDb
) : BeerCache {
    override fun saveBeerInfo(date: DateTime): Completable {
        return Completable.defer {
            appDb.beerDao().insertInfo(BeersEntity(0,date.toString()))
        }
    }

    override fun getDateInfo(): Maybe<String> {
        return appDb.beerDao().getDate().map {
            it.date
        }
    }

}