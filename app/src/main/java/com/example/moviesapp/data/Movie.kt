package com.example.moviesapp.data

import java.util.*

data class Movie(
     val id:Int,
     val moviePosterPath: String?,
     val movieName: String,
     val overview: String,
     val voteCount: Int,
     val voteAverage: Float,
     val firstAirDate: Date?
)