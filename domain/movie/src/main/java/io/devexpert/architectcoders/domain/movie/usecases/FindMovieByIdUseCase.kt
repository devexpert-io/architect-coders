package io.devexpert.architectcoders.domain.movie.usecases

import io.devexpert.architectcoders.domain.movie.data.MoviesRepository
import io.devexpert.architectcoders.domain.movie.entities.Movie
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class FindMovieByIdUseCase(private val repository: MoviesRepository) {
    operator fun invoke(id: Int): Flow<Movie> = repository.findMovieById(id)
}