package io.devexpert.architectcoders.data.datasource

import io.devexpert.architectcoders.domain.Movie

interface MoviesRemoteDataSource {
    suspend fun fetchPopularMovies(region: String): List<Movie>

    suspend fun findMovieById(id: Int): Movie
}