package io.devexpert.architectcoders.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.devexpert.architectcoders.data.Movie
import io.devexpert.architectcoders.data.MoviesRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository: MoviesRepository = MoviesRepository()

    var state by mutableStateOf(UiState())
        private set

    fun onUiReady(region: String) {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(loading = false, movies = repository.fetchPopularMovies(region))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movies: List<Movie> = emptyList()
    )
}