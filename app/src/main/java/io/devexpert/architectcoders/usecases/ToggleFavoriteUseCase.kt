package io.devexpert.architectcoders.usecases

import io.devexpert.architectcoders.data.MoviesRepository
import io.devexpert.architectcoders.domain.Movie

class ToggleFavoriteUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke(movie: Movie) {
        repository.toggleFavorite(movie)
    }
}