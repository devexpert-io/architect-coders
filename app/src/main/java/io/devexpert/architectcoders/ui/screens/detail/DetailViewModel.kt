package io.devexpert.architectcoders.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.devexpert.architectcoders.data.Movie
import io.devexpert.architectcoders.data.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(private val id: Int) : ViewModel() {
    private val repository: MoviesRepository = MoviesRepository()

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    data class UiState(
        val loading: Boolean = false,
        val movie: Movie? = null,
        val message: String? = null
    )

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(loading = false, movie = repository.findMovieById(id))
        }
    }

    fun onFavoriteClicked() {
        _state.update { it.copy(message = "Favorite clicked") }
    }

    fun onMessageShown() {
        _state.update { it.copy(message = null) }
    }
}