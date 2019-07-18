package com.example.luis.remote.beer.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResultRemote(
    @Expose
    @SerializedName("results")
    val results: List<MovieRemote>?
)