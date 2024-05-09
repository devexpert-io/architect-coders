package io.devexpert.architectcoders.usecases

import io.devexpert.architectcoders.data.MoviesRepository
import io.devexpert.architectcoders.domain.Movie
import kotlinx.coroutines.flow.Flow

class FetchMoviesUseCase(private val moviesRepository: MoviesRepository) {
    operator fun invoke(): Flow<List<Movie>> = moviesRepository.movies
}