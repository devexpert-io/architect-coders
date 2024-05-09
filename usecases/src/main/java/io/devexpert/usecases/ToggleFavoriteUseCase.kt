package io.devexpert.usecases

import io.devexpert.data.MoviesRepository
import io.devexpert.domain.Movie

class ToggleFavoriteUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke(movie: Movie) {
        repository.toggleFavorite(movie)
    }
}