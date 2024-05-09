package io.devexpert.architectcoders.domain

import io.devexpert.architectcoders.data.Movie
import io.devexpert.architectcoders.data.MoviesRepository
import kotlinx.coroutines.flow.Flow

class FindMovieByIdUseCase(private val repository: MoviesRepository) {
    operator fun invoke(id: Int): Flow<Movie> = repository.findMovieById(id)
}