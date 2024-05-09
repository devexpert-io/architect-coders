package io.devexpert.architectcoders.data.datasource

import io.devexpert.architectcoders.domain.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {
    val movies: Flow<List<Movie>>
    fun findMovieById(id: Int): Flow<Movie?>
    suspend fun save(movies: List<Movie>)
}