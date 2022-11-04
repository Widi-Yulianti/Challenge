package com.example.moviesapp.repository

import com.example.moviesapp.data.Movie
import com.example.moviesapp.data.remote.MovieDto
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


interface MovieMapper {
    fun mapToMovie(movieDto: MovieDto): Movie
}

class MovieMapperImpl @Inject constructor() : MovieMapper {

    override fun mapToMovie(movieDto: MovieDto): Movie {
        val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        return Movie(
            movieDto.id,
            movieDto.moviePosterPath,
            movieDto.movieName,
            movieDto.overview,
            movieDto.voteCount,
            movieDto.voteAverage,
            parseDate(dateFormat, movieDto)
        )
    }

    private fun parseDate(
        dateFormat: SimpleDateFormat,
        movieDto: MovieDto
    ): Date? {
        return try {
            dateFormat.parse(movieDto.firstAirDate)
        } catch (ex: Exception) {
            null
        }
    }
}
