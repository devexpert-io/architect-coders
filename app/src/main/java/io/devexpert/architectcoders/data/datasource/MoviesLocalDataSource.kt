package io.devexpert.architectcoders.data.datasource

import io.devexpert.architectcoders.data.Movie
import io.devexpert.architectcoders.data.datasource.database.MoviesDao

class MoviesLocalDataSource(private val moviesDao: MoviesDao) {

    suspend fun fetchPopularMovies(): List<Movie> = moviesDao.fetchPopularMovies()

    suspend fun findMovieById(id: Int): Movie? = moviesDao.findMovieById(id)

    suspend fun isEmpty(): Boolean = moviesDao.countMovies() == 0

    suspend fun save(movies: List<Movie>) = moviesDao.save(movies)
}
