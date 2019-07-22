package com.example.luis.test.dagger.module

import com.example.luis.domain.movies.repository.MovieRepository
import com.example.luis.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DomainModule {

    @Binds
    abstract fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository
}