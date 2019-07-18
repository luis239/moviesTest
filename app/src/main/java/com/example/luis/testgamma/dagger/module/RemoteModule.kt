package com.example.luis.testgamma.dagger.module

import com.example.luis.remote.common.Api
import com.example.luis.remote.common.ApiService
import com.example.luis.remote.ApiServiceImpl
import com.example.luis.remote.beer.BeerRemoteImpl
import com.example.luis.repository.beer.repository.BeerRemote
import com.example.luis.testgamma.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
abstract class RemoteModule {


    @Module
    companion object {

        @Provides
        @JvmStatic
        @Named("autoApi")
        fun provideApiService(): ApiService {
            return ApiServiceImpl(
                BuildConfig.URL_BASE,
                BuildConfig.DEBUG)
        }

        @Provides
        @JvmStatic
        @Singleton
        fun provideApi(@Named("autoApi") apiService: ApiService): Api {
            return apiService.create(Api::class.java)
        }
    }

    @Binds
    @Singleton
    abstract fun bindBeerRemote(userRemote: BeerRemoteImpl): BeerRemote
}