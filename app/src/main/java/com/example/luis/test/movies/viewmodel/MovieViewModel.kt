package com.example.luis.test.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.luis.domain.movies.*
import com.example.luis.domain.movies.model.MovieModel
import com.example.luis.test.common.Resource
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val getMoviesListUseCase: GetMoviesListUseCase,
    private val updateFavoriteMovieUseCase: UpdateFavoriteMovieUseCase,
    private val updateWatchListMovieUseCase: UpdateWatchListMovieUseCase,
    private val getWatchListMoviesUseCase: GetWatchListMoviesUseCase,
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase
):ViewModel() {

    val moviesLiveData = MutableLiveData<Resource<List<MovieModel>>>()
    val favoriteListLiveData = MutableLiveData<Resource<List<MovieModel>>>()
    val favoriteLiveData = MutableLiveData<Nothing>()



    fun getMovies() {
        moviesLiveData.postValue(Resource.loading())
        getMoviesListUseCase.execute(object : DisposableSingleObserver<List<MovieModel>>(){
            override fun onSuccess(t: List<MovieModel>) {
                moviesLiveData.postValue(Resource.next(t))
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                moviesLiveData.postValue(Resource.error(e.message))
            }

        },GetMoviesListUseCase.Params(false))
    }

    fun getFavoriteMovies() {
        favoriteListLiveData.postValue(Resource.loading())
        getFavoriteMoviesUseCase.execute(object : DisposableMaybeObserver<List<MovieModel>>(){
            override fun onComplete() {

            }

            override fun onSuccess(t: List<MovieModel>) {
                favoriteListLiveData.postValue(Resource.next(t))
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                favoriteListLiveData.postValue(Resource.error(e.message))
            }

        })
    }

    fun updateFavorite(id:Int,isFavorite:Boolean){
        updateFavoriteMovieUseCase.execute(object :DisposableCompletableObserver(){
            override fun onComplete() {
                favoriteLiveData.postValue(null)
                getFavoriteMovies()
                //getMovies()
            }

            override fun onError(e: Throwable) {

            }

        }, UpdateFavoriteMovieUseCase.Params(id,isFavorite))
    }

    fun getWatchList() {
        getWatchListMoviesUseCase.execute(object : DisposableMaybeObserver<List<MovieModel>>(){
            override fun onComplete() {

            }

            override fun onSuccess(t: List<MovieModel>) {
                moviesLiveData.postValue(Resource.next(t))
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                moviesLiveData.postValue(Resource.error(e.message))
            }

        })
    }

    fun updateWatchList(id:Int,isWatchList:Boolean){
        updateWatchListMovieUseCase.execute(object :DisposableCompletableObserver(){
            override fun onComplete() {
            }

            override fun onError(e: Throwable) {
            }

        }, UpdateWatchListMovieUseCase.Params(id,isWatchList))
    }
}