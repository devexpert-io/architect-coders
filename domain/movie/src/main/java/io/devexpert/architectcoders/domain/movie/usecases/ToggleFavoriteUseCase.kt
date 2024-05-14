package io.devexpert.architectcoders.domain.movie.usecases

import io.devexpert.architectcoders.domain.movie.data.MoviesRepository
import io.devexpert.architectcoders.domain.movie.entities.Movie

class ToggleFavoriteUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke(movie: Movie) {
        repository.toggleFavorite(movie)
    }
}