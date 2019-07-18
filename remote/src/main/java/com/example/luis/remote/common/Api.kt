package com.example.luis.remote.common

import com.example.luis.remote.beer.model.MovieRemote
import com.example.luis.remote.beer.model.ResultRemote
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("movie/now_playing")
    fun getListMovies(@Query("api_key") apiKey :String, @Query("language")language:String,
                      @Query("page") page:Int): Single<ResultRemote>
}