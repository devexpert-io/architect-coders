package io.devexpert.usecases

import io.devexpert.data.MoviesRepository
import io.devexpert.domain.Movie
import kotlinx.coroutines.flow.Flow

class FindMovieByIdUseCase(private val repository: MoviesRepository) {
    operator fun invoke(id: Int): Flow<Movie> = repository.findMovieById(id)
}