package com.example.luis.remote.movie.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieRemote(
    @Expose
    @SerializedName("id")
    val id:Int?,
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