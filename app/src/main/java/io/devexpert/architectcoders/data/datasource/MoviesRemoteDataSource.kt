package io.devexpert.architectcoders.data.datasource

import io.devexpert.architectcoders.data.datasource.remote.MoviesClient
import io.devexpert.architectcoders.data.datasource.remote.RemoteMovie
import io.devexpert.architectcoders.domain.Movie

class MoviesRemoteDataSource {

    suspend fun fetchPopularMovies(region: String): List<Movie> =
        MoviesClient.instance.fetchPopularMovies(region)
            .results
            .map { it.toDomainModel() }

    suspend fun findMovieById(id: Int): Movie =
        MoviesClient.instance.fetchMovieById(id).toDomainModel()
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