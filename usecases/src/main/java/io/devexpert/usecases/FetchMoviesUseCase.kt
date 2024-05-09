package io.devexpert.usecases

import io.devexpert.data.MoviesRepository
import io.devexpert.domain.Movie
import kotlinx.coroutines.flow.Flow

class FetchMoviesUseCase(private val moviesRepository: MoviesRepository) {
    operator fun invoke(): Flow<List<Movie>> = moviesRepository.movies
}