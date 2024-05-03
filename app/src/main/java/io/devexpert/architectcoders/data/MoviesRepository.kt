package io.devexpert.architectcoders.data

import io.devexpert.architectcoders.data.datasource.MoviesLocalDataSource
import io.devexpert.architectcoders.data.datasource.MoviesRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class MoviesRepository(
    private val regionRepository: RegionRepository,
    private val localDataSource: MoviesLocalDataSource,
    private val remoteDataSource: MoviesRemoteDataSource
) {
    val movies: Flow<List<Movie>> = localDataSource.movies.transform { localMovies ->
        val movies = localMovies.takeIf { it.isNotEmpty() }
            ?: remoteDataSource.fetchPopularMovies(regionRepository.findLastRegion()).also {
                localDataSource.save(it)
            }
        emit(movies)
    }

    fun findMovieById(id: Int): Flow<Movie> =
        localDataSource.findMovieById(id).transform { localMovie ->
            val movie = localMovie
                ?: remoteDataSource.findMovieById(id).also { localDataSource.save(listOf(it)) }
            emit(movie)
        }
}

