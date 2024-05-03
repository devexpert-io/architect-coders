package io.devexpert.architectcoders.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.devexpert.architectcoders.data.Movie
import io.devexpert.architectcoders.data.MoviesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModel(repository: MoviesRepository) : ViewModel() {

    private val uiReady = MutableStateFlow(false)

    val state: StateFlow<UiState> = uiReady
        .filter { it }
        .flatMapLatest { repository.movies }
        .map { UiState(movies = it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState(loading = true)
        )

    fun onUiReady() {
        uiReady.value = true
    }

    data class UiState(
        val loading: Boolean = false,
        val movies: List<Movie> = emptyList()
    )
}