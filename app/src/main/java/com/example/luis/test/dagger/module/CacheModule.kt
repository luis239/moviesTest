package com.example.luis.test.dagger.module

import android.content.Context
import com.example.luis.cache.movies.MoviesCacheImple
import com.example.luis.cache.room.AppDb
import com.example.luis.repository.movie.repository.MovieCache
import dagger.Binds
import dagger.Module
import dagger.Provides
@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(context: Context): AppDb {
            return AppDb.getInstance(context)
        }
    }

    @Binds
    abstract fun bindMoviesCache(moviesCache: MoviesCacheImple): MovieCache
}