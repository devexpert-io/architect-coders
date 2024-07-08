package io.devexpert.architectcoders.ui.detail

import app.cash.turbine.test
import io.devexpert.architectcoders.data.buildMoviesRepositoryWith
import io.devexpert.architectcoders.domain.movie.sampleMovie
import io.devexpert.architectcoders.domain.movie.sampleMovies
import io.devexpert.architectcoders.domain.movie.usecases.FindMovieByIdUseCase
import io.devexpert.architectcoders.domain.movie.usecases.ToggleFavoriteUseCase
import io.devexpert.architectcoders.testrules.CoroutinesTestRule
import io.devexpert.architectcoders.ui.common.Result
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailIntegrationTests {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var vm: DetailViewModel

    @Before
    fun setUp() {
        val moviesRepository = buildMoviesRepositoryWith(localData = sampleMovies(1, 2))
        vm = DetailViewModel(
            2,
            FindMovieByIdUseCase(moviesRepository),
            ToggleFavoriteUseCase(moviesRepository)
        )
    }

    @Test
    fun `UI is updated with the movie on start`() = runTest {
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(sampleMovie(2)), awaitItem())
        }
    }

    @Test
    fun `Favorite is updated in local data source`() = runTest {
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(sampleMovie(2)), awaitItem())

            vm.onFavoriteClicked()
            runCurrent()

            assertEquals(Result.Success(sampleMovie(2).copy(isFavorite = true)), awaitItem())
        }
    }
}