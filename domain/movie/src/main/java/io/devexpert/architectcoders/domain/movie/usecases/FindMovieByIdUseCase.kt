package io.devexpert.architectcoders.domain.movie.usecases

import io.devexpert.architectcoders.domain.movie.data.MoviesRepository
import io.devexpert.architectcoders.domain.movie.entities.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindMovieByIdUseCase @Inject constructor(private val repository: MoviesRepository) {
    operator fun invoke(id: Int): Flow<Movie> = repository.findMovieById(id)
}