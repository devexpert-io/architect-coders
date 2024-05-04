package io.devexpert.architectcoders.ui.screens.detail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.devexpert.architectcoders.Result
import io.devexpert.architectcoders.data.Movie

@OptIn(ExperimentalMaterial3Api::class)
class DetailState(
    private val state: Result<Movie>,
    val scrollBehavior: TopAppBarScrollBehavior,
    val snackbarHostState: SnackbarHostState
) {
    val movie: Movie?
        get() = (state as? Result.Success)?.data

    val topBarTitle: String
        get() = movie?.title ?: ""
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberDetailState(
    state: Result<Movie>,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) = remember(state) { DetailState(state, scrollBehavior, snackbarHostState) }