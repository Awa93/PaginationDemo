package com.example.movieapp.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
        @SerializedName("Response")
        @Expose
        val Response: String,
        @SerializedName("Search")
        @Expose
        val Movie: List<Movie>,
        @SerializedName("totalResults")
        @Expose
        val totalResults: String
)