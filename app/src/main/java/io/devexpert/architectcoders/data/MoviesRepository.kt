package io.devexpert.architectcoders.data

import io.devexpert.architectcoders.data.datasource.MoviesLocalDataSource
import io.devexpert.architectcoders.data.datasource.MoviesRemoteDataSource

class MoviesRepository(
    private val regionRepository: RegionRepository,
    private val localDataSource: MoviesLocalDataSource,
    private val remoteDataSource: MoviesRemoteDataSource
) {
    suspend fun fetchPopularMovies(): List<Movie> {
        if (localDataSource.isEmpty()) {
            val movies = remoteDataSource.fetchPopularMovies(regionRepository.findLastRegion())
            localDataSource.save(movies)
        }
        return localDataSource.fetchPopularMovies()
    }

    suspend fun findMovieById(id: Int): Movie {
        if(localDataSource.findMovieById(id) == null) {
            val movie = remoteDataSource.findMovieById(id)
            localDataSource.save(listOf(movie))
        }
        return checkNotNull(localDataSource.findMovieById(id))
    }
}

