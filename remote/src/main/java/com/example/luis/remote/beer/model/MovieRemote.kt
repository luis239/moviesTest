package com.example.luis.remote.beer.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieRemote(
    @Expose
    @SerializedName("vote_average")
    val rating: Double?,
    @Expose
    @SerializedName("poster_path")
    val imageUrl: String?,
    @Expose
    @SerializedName("title")
    val title: String?
)