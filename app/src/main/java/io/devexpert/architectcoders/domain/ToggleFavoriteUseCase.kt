package io.devexpert.architectcoders.domain

import io.devexpert.architectcoders.data.Movie
import io.devexpert.architectcoders.data.MoviesRepository

class ToggleFavoriteUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke(movie: Movie) {
        repository.toggleFavorite(movie)
    }
}