package io.devexpert.architectcoders.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.devexpert.architectcoders.Result
import io.devexpert.architectcoders.domain.Movie
import io.devexpert.architectcoders.stateAsResultIn
import io.devexpert.architectcoders.usecases.FetchMoviesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModel(private val fetchMoviesUseCase: FetchMoviesUseCase) : ViewModel() {

    private val uiReady = MutableStateFlow(false)

    val state: StateFlow<Result<List<Movie>>> = uiReady
        .filter { it }
        .flatMapLatest { fetchMoviesUseCase() }
        .stateAsResultIn(viewModelScope)

    fun onUiReady() {
        uiReady.value = true
    }
}