package io.devexpert.architectcoders.data.datasource

import io.devexpert.architectcoders.data.datasource.database.DbMovie
import io.devexpert.architectcoders.data.datasource.database.MoviesDao
import io.devexpert.architectcoders.domain.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesLocalDataSource(private val moviesDao: MoviesDao) {

    val movies: Flow<List<Movie>> = moviesDao.fetchPopularMovies().map { it.toDomainMovies() }

    fun findMovieById(id: Int): Flow<Movie?> =
        moviesDao.findMovieById(id).map { it?.toDomainMovie() }

    suspend fun save(movies: List<Movie>) = moviesDao.save(movies.toDbMovies())
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
