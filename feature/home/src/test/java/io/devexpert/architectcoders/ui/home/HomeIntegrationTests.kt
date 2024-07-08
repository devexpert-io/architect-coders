package io.devexpert.architectcoders.ui.home

import app.cash.turbine.test
import io.devexpert.architectcoders.data.buildMoviesRepositoryWith
import io.devexpert.architectcoders.domain.movie.entities.Movie
import io.devexpert.architectcoders.domain.movie.sampleMovies
import io.devexpert.architectcoders.domain.movie.usecases.FetchMoviesUseCase
import io.devexpert.architectcoders.testrules.CoroutinesTestRule
import io.devexpert.architectcoders.ui.common.Result
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class HomeIntegrationTests {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `data is loaded from server when local source is empty`() = runTest {
        val remoteData = sampleMovies(1, 2)
        val vm = buildViewModelWith(
            localData = emptyList(),
            remoteData = remoteData
        )

        vm.onUiReady()

        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(emptyList<Movie>()), awaitItem())
            assertEquals(Result.Success(remoteData), awaitItem())
        }
    }

    @Test
    fun `data is loaded from local source when available`() = runTest {
        val localData = sampleMovies(1, 2)
        val vm = buildViewModelWith(localData = localData)

        vm.onUiReady()

        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(localData), awaitItem())
        }

    }

}

private fun buildViewModelWith(
    localData: List<Movie> = emptyList(),
    remoteData: List<Movie> = emptyList()
): HomeViewModel {
    val fetchMoviesUseCase = FetchMoviesUseCase(buildMoviesRepositoryWith(localData, remoteData))
    return HomeViewModel(fetchMoviesUseCase)
}