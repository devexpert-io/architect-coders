package io.devexpert.architectcoders.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.devexpert.architectcoders.domain.movie.entities.Movie
import io.devexpert.architectcoders.domain.movie.usecases.FindMovieByIdUseCase
import io.devexpert.architectcoders.domain.movie.usecases.ToggleFavoriteUseCase
import io.devexpert.architectcoders.ui.common.Result
import io.devexpert.architectcoders.ui.common.ifSuccess
import io.devexpert.architectcoders.ui.common.stateAsResultIn
import io.devexpert.architectcoders.ui.detail.di.MovieId
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    @MovieId id: Int,
    findMovieByIdUseCase: FindMovieByIdUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    val state: StateFlow<Result<Movie>> = findMovieByIdUseCase(id)
        .stateAsResultIn(scope = viewModelScope)

    fun onFavoriteClicked() {
        state.value.ifSuccess {
            viewModelScope.launch {
                toggleFavoriteUseCase(it)
            }
        }
    }
}