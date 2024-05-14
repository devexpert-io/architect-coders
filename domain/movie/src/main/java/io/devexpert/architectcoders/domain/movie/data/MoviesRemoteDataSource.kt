package io.devexpert.architectcoders.domain.movie.data

import io.devexpert.architectcoders.domain.movie.entities.Movie

interface MoviesRemoteDataSource {
    suspend fun fetchPopularMovies(region: String): List<Movie>

    suspend fun findMovieById(id: Int): Movie
}