package io.devexpert.architectcoders.framework.movie.database

import io.devexpert.architectcoders.domain.movie.data.MoviesLocalDataSource
import io.devexpert.architectcoders.domain.movie.entities.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class MoviesRoomDataSource(private val moviesDao: MoviesDao) :
    MoviesLocalDataSource {

    override val movies: Flow<List<Movie>> =
        moviesDao.fetchPopularMovies().map { it.toDomainMovies() }

    override fun findMovieById(id: Int): Flow<Movie?> =
        moviesDao.findMovieById(id).map { it?.toDomainMovie() }

    override suspend fun save(movies: List<Movie>) = moviesDao.save(movies.toDbMovies())
}

private fun DbMovie.toDomainMovie() = Movie(
    id = id,
    title = title,
    overview = overview,
    poster = poster,
    backdrop = backdrop,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    isFavorite = isFavorite,
    originalTitle = originalTitle,
    originalLanguage = originalLanguage,
    popularity = popularity
)

private fun List<DbMovie>.toDomainMovies() = map { it.toDomainMovie() }

private fun Movie.toDbMovie() = DbMovie(
    id = id,
    title = title,
    overview = overview,
    poster = poster,
    backdrop = backdrop,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    isFavorite = isFavorite,
    originalTitle = originalTitle,
    originalLanguage = originalLanguage,
    popularity = popularity
)

private fun List<Movie>.toDbMovies() = map { it.toDbMovie() }
