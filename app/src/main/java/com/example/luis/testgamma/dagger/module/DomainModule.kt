package com.example.luis.testgamma.dagger.module

import com.example.luis.domain.beer.repository.BeerRepository
import com.example.luis.repository.BeerRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DomainModule {

    @Binds
    abstract fun bindBeerRepository(beerRepositoryImpl: BeerRepositoryImpl): BeerRepository
}