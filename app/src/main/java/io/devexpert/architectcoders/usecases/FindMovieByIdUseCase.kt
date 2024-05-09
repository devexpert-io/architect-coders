package io.devexpert.architectcoders.usecases

import io.devexpert.architectcoders.data.MoviesRepository
import io.devexpert.architectcoders.domain.Movie
import kotlinx.coroutines.flow.Flow

class FindMovieByIdUseCase(private val repository: MoviesRepository) {
    operator fun invoke(id: Int): Flow<Movie> = repository.findMovieById(id)
}