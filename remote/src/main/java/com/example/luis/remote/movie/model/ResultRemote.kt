package com.example.luis.remote.movie.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResultRemote(
    @Expose
    @SerializedName("results")
    val results: List<MovieRemote>?
)