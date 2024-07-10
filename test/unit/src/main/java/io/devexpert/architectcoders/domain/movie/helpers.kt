package io.devexpert.architectcoders.domain.movie

import io.devexpert.architectcoders.domain.movie.entities.Movie

fun sampleMovie(id: Int) = Movie(
    id = id,
    title = "Title $id",
    overview = "Overview $id",
    releaseDate = "01/01/2025",
    poster = "",
    backdrop = "",
    originalTitle = "EN",
    originalLanguage = "Title",
    popularity = 5.0,
    voteAverage = 5.1,
    isFavorite = false
)

fun sampleMovies(vararg ids: Int) = ids.map { sampleMovie(it) }