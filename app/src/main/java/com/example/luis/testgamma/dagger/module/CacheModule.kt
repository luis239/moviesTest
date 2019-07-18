package com.example.luis.testgamma.dagger.module

import android.content.Context
import com.example.luis.cache.beers.BeersCacheImple
import com.example.luis.cache.room.AppDb
import com.example.luis.repository.beer.repository.BeerCache
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
    abstract fun bindBeersCache(beersCache: BeersCacheImple): BeerCache
}