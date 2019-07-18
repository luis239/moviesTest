package com.example.luis.testgamma.dagger.module


import com.example.luis.domain.common.executor.PostExecutionThread
import com.example.luis.testgamma.common.UiThread
import com.example.luis.testgamma.beers.BeersActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun bindBeerActivity():BeersActivity

}