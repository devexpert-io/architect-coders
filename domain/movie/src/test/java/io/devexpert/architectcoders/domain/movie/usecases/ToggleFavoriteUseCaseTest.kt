package io.devexpert.architectcoders.domain.movie.usecases

import io.devexpert.architectcoders.domain.movie.data.MoviesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ToggleFavoriteUseCaseTest {

    @Test
    fun `Invoke calls repository`(): Unit = runBlocking {
        val movie = sampleMovie(1)
        val repository = mock<MoviesRepository>()
        val useCase = ToggleFavoriteUseCase(repository)

        useCase(movie)

        verify(repository).toggleFavorite(movie)
    }
}