package com.example.luis.repository.beer.repository

import io.reactivex.Completable
import io.reactivex.Maybe
import org.joda.time.DateTime

interface BeerCache {
    fun saveBeerInfo(date: DateTime):Completable
    fun getDateInfo(): Maybe<String>
}