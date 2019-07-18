package com.example.luis.repository.beer.repository

import com.example.luis.repository.beer.model.RemoteData
import io.reactivex.Single

interface BeerRemote {

        fun getBeers() : Single<List<RemoteData>>
}