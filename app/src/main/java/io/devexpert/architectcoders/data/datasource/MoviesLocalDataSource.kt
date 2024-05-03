package io.devexpert.architectcoders.data.datasource

import io.devexpert.architectcoders.data.Movie
import io.devexpert.architectcoders.data.datasource.database.MoviesDao
import kotlinx.coroutines.flow.Flow

class MoviesLocalDataSource(private val moviesDao: MoviesDao) {

    val movies: Flow<List<Movie>> = moviesDao.fetchPopularMovies()

    fun findMovieById(id: Int): Flow<Movie?> = moviesDao.findMovieById(id)

    suspend fun save(movies: List<Movie>) = moviesDao.save(movies)
}
