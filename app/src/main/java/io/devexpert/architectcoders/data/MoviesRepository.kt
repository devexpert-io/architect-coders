package io.devexpert.architectcoders.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MoviesRepository {
    suspend fun fetchPopularMovies(): List<Movie> = withContext(Dispatchers.IO) {
        delay(1000)
        movies
    }
}