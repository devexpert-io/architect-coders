package io.devexpert.architectcoders.framework

import io.devexpert.architectcoders.framework.remote.MoviesService
import io.devexpert.architectcoders.framework.remote.RemoteMovie
import io.devexpert.data.datasource.MoviesRemoteDataSource
import io.devexpert.domain.Movie

class MoviesServerDataSource(private val moviesService: MoviesService) : MoviesRemoteDataSource {

    override suspend fun fetchPopularMovies(region: String): List<Movie> =
        moviesService.fetchPopularMovies(region)
            .results
            .map { it.toDomainModel() }

    override suspend fun findMovieById(id: Int): Movie =
        moviesService.fetchMovieById(id).toDomainModel()
}

private fun RemoteMovie.toDomainModel() = Movie(
    id,
    title,
    overview,
    releaseDate,
    "https://image.tmdb.org/t/p/w185/$posterPath",
    backdropPath?.let { "https://image.tmdb.org/t/p/w780/$it" },
    originalLanguage,
    originalTitle,
    popularity,
    voteAverage,
    false
)