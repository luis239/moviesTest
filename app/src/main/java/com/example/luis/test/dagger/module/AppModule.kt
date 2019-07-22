package com.example.luis.test.dagger.module


import com.example.luis.domain.common.executor.PostExecutionThread
import com.example.luis.test.movies.FavoriteMoviesFragment
import com.example.luis.test.common.UiThread
import com.example.luis.test.movies.MoviesFragment
import com.example.luis.test.movies.WatchListActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun bindMoviesFragment():MoviesFragment

    @ContributesAndroidInjector
    abstract fun bindFavoriteMoviesFragment():FavoriteMoviesFragment

    @ContributesAndroidInjector
    abstract fun bindWatchListActivity():WatchListActivity

}