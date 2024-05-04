package io.devexpert.architectcoders.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.devexpert.architectcoders.Result
import io.devexpert.architectcoders.data.Movie
import io.devexpert.architectcoders.data.MoviesRepository
import io.devexpert.architectcoders.ifSuccess
import io.devexpert.architectcoders.stateAsResultIn
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: MoviesRepository, id: Int) : ViewModel() {

    val state: StateFlow<Result<Movie>> = repository.findMovieById(id)
        .stateAsResultIn(scope = viewModelScope)

    fun onFavoriteClicked() {
        state.value.ifSuccess {
            viewModelScope.launch {
                repository.toggleFavorite(it)
            }
        }
    }
}