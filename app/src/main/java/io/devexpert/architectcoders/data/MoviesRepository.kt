package io.devexpert.architectcoders.data

import io.devexpert.architectcoders.data.datasource.MoviesRemoteDataSource

class MoviesRepository(
    private val regionRepository: RegionRepository,
    private val remoteDataSource: MoviesRemoteDataSource
) {
    suspend fun fetchPopularMovies(): List<Movie> =
        remoteDataSource.fetchPopularMovies(regionRepository.findLastRegion())

    suspend fun findMovieById(id: Int): Movie = remoteDataSource.findMovieById(id)
}

